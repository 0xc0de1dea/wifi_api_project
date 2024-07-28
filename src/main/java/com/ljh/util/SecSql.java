package com.ljh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecSql {
    private StringBuilder sqlBuilder;
    private List<Object> datas;

    @Override
    public String toString(){
        return "rawSql=" + getRawSql() + ", data=" + datas;
    }

    public SecSql(){
        sqlBuilder = new StringBuilder();
        datas = new ArrayList<>();
    }

    private boolean isInsert() {
        return getFormat().startsWith("INSERT");
    }

    public SecSql append(Object... args){
        if (args.length > 0){
            String sqlBit = (String) args[0];
            sqlBuilder.append(sqlBit).append(" ");
        }

        for (int i = 1; i < args.length; i++){
            datas.add(args[i]);
        }

        return this;
    }

    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement stmt = null;

        if (isInsert()){
            stmt = connection.prepareStatement(getFormat(), Statement.RETURN_GENERATED_KEYS);
        } else {
            stmt = connection.prepareStatement(getFormat());
        }

        for (int i = 0; i < datas.size(); i++){
            Object data = datas.get(i);

            if (data instanceof Integer){
                stmt.setInt(i + 1, (int)data);
            } else if (data instanceof Long){
                stmt.setLong(i + 1, (long)data);
            } else if (data instanceof Double){
                stmt.setDouble(i + 1, (double)data);
            } else if (data instanceof String){
                stmt.setString(i + 1, (String)data);
            }
        }

        if (Mysql.isDevMode()){
            System.out.println("rawSql : " + getRawSql());
        }

        return stmt;
    }

    public String getFormat(){
        return sqlBuilder.toString().trim();
    }

    public String getRawSql(){
        String rawSql = getFormat();

        for (int i = 0; i < datas.size(); i++){
            Object data = datas.get(i);
            rawSql = rawSql.replaceFirst("\\?", "'" + data + "'");
        }

        return rawSql;
    }
}
