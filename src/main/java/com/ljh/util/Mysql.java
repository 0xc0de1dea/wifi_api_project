package com.ljh.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mysql {
    private static String dbHost;
    private static String dbLoginId;
    private static String dbLoginPw;
    private static String dbName;
    private static boolean isDevMode;

    private static Map<Long, Connection> connections;

    static {
        connections = new HashMap<>();
    }

    public static void setDevMode(boolean isDevMode){
        Mysql.isDevMode = isDevMode;
    }

    public static boolean isDevMode(){
        return isDevMode;
    }

    public static void setDBInfo(String dbHost, String dbLoginId, String dbLoginPw, String dbName){
        Mysql.dbHost = dbHost;
        Mysql.dbLoginId = dbLoginId;
        Mysql.dbLoginPw = dbLoginPw;
        Mysql.dbName = dbName;
    }

    public static void closeConnection(){
        long currentThreadId = Thread.currentThread().getId();

        if (!connections.containsKey(currentThreadId)) {
            return;
        }

        Connection connection = connections.get(currentThreadId);

        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        long currentThreadId = Thread.currentThread().getId();

        if (!connections.containsKey(currentThreadId)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new MysqlException(e);
            }

            Connection connection = null;

            String url = "jdbc:mysql://" + dbHost + "/" + dbName
                    + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60";

            try {
                connection = DriverManager.getConnection(url, dbLoginId, dbLoginPw);
                connections.put(currentThreadId, connection);
            } catch (SQLException e) {
                closeConnection();
                throw new MysqlException(e);
            }
        }

        return connections.get(currentThreadId);
    }

    public static Map<String, Object> selectRow(SecSql sql){
        List<Map<String, Object>> rows = selectRows(sql);

        if (rows.size() == 0) return new HashMap<>();

        return rows.get(0);
    }

    public static List<Map<String, Object>> selectRows(SecSql sql){
        List<Map<String, Object>> rows = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = sql.getPreparedStatement(getConnection());
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int colSize = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    String colName = metaData.getColumnName(colIdx + 1);
                    Object colVal = rs.getObject(colName);

                    if (colVal instanceof Integer){
                        int numVal = (int)colVal;
                        row.put(colName, numVal);
                    }
                    if (colVal instanceof Long){
                        long numVal = (long)colVal;
                        row.put(colName, numVal);
                    } else if (colVal instanceof Timestamp){
                        String dateVal = colVal.toString();
                        dateVal = dateVal.substring(0, dateVal.length() - 2);
                        row.put(colName, dateVal);
                    } else {
                        row.put(colName, colVal);
                    }
                }

                rows.add(row);
            }
        } catch (SQLException e){
            closeConnection();
            throw new MysqlException(e);
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    closeConnection();
                    throw new MysqlException(e);
                }
            }

            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    closeConnection();
                    throw new MysqlException(e);
                }
            }
        }

        return rows;
    }

    public static int selectRowIntValue(SecSql sql){
        Map<String, Object> row = selectRow(sql);

        for (String key : row.keySet()){
            return (int)row.get(key);
        }

        return -1;
    }

    public static String selectRowStringValue(SecSql sql){
        Map<String, Object> row = selectRow(sql);

        for (String key : row.keySet()) {
            return (String) row.get(key);
        }

        return "";
    }

    public static boolean selectRowBooleanValue(SecSql sql) {
        Map<String, Object> row = selectRow(sql);

        for (String key : row.keySet()) {
            return ((int) row.get(key)) == 1;
        }

        return false;
    }

    public static int insert(SecSql sql){
        int id = -1;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = sql.getPreparedStatement(getConnection());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            closeConnection();
            throw new MysqlException(e);
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    closeConnection();
                    throw new MysqlException(e);
                }
            }

            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    closeConnection();
                    throw new MysqlException(e);
                }
            }
        }

        return id;
    }

    public static int update(SecSql sql){
        int afffectedRows = 0;

        PreparedStatement stmt = null;

        try {
            stmt = sql.getPreparedStatement(getConnection());
            afffectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            closeConnection();
            throw new MysqlException(e);
        } finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    closeConnection();
                    throw new MysqlException(e);
                }
            }
        }

        return afffectedRows;
    }

    public static int delete(SecSql sql){
        return update(sql);
    }
}
