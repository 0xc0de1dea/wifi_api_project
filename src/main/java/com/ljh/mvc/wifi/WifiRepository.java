package com.ljh.mvc.wifi;

import com.ljh.container.Container;
import com.ljh.dto.BookMarkGroupDTO;
import com.ljh.dto.RowDTO;
import com.ljh.mvc.history.HistoryRepository;
import com.ljh.util.Mysql;
import com.ljh.util.SecSql;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WifiRepository {
    public int insert(List<RowDTO> rowList) {
        int cnt = 0;

        for (int i = 0; i < rowList.size(); i++) {
            RowDTO rowDTO = rowList.get(i);

            SecSql sql = new SecSql();
            sql.append("INSERT INTO wifi_information");
            sql.append("(DIST, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,");
            sql.append("X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR,");
            sql.append("X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM)");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    0,
                    rowDTO.getX_SWIFI_MGR_NO(),
                    rowDTO.getX_SWIFI_WRDOFC(),
                    rowDTO.getX_SWIFI_MAIN_NM(),
                    rowDTO.getX_SWIFI_ADRES1(),
                    rowDTO.getX_SWIFI_ADRES2(),
                    rowDTO.getX_SWIFI_INSTL_FLOOR(),
                    rowDTO.getX_SWIFI_INSTL_TY(),
                    rowDTO.getX_SWIFI_INSTL_MBY(),
                    rowDTO.getX_SWIFI_SVC_SE(),
                    rowDTO.getX_SWIFI_CMCWR(),
                    rowDTO.getX_SWIFI_CNSTC_YEAR(),
                    rowDTO.getX_SWIFI_INOUT_DOOR(),
                    rowDTO.getX_SWIFI_REMARS3(),
                    rowDTO.getLAT(),
                    rowDTO.getLNT(),
                    rowDTO.getWORK_DTTM());

            Mysql.insert(sql);
            cnt++;
        }

        return cnt;
    }

    public List<RowDTO> getNearWifiLimit20(String lat, String lnt){
        HistoryRepository historyRepository = Container.historyRepository;
        historyRepository.insert(lat, lnt);

        List<RowDTO> list = new ArrayList<>();

        SecSql sql = new SecSql();
        sql.append("SELECT *,");
        sql.append("round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)", lat);
        sql.append("-radians(?))+sin(radians(?))*sin(radians(LAT))), 4)", lnt, lat);
        sql.append("AS distance");
        sql.append("FROM wifi_information");
        sql.append("ORDER BY distance");
        sql.append("LIMIT 20");

        List<Map<String, Object>> rows = Mysql.selectRows(sql);

        for (Map<String, Object> row : rows){
            RowDTO rowDTO = new RowDTO();
            rowDTO.setID((int)row.get("ID"));
            rowDTO.setDIST((double)row.get("distance"));
            rowDTO.setX_SWIFI_MGR_NO((String)row.get("X_SWIFI_MGR_NO"));
            rowDTO.setX_SWIFI_WRDOFC((String)row.get("X_SWIFI_WRDOFC"));
            rowDTO.setX_SWIFI_MAIN_NM((String)row.get("X_SWIFI_MAIN_NM"));
            rowDTO.setX_SWIFI_ADRES1((String)row.get("X_SWIFI_ADRES1"));
            rowDTO.setX_SWIFI_ADRES2((String)row.get("X_SWIFI_ADRES2"));
            rowDTO.setX_SWIFI_INSTL_FLOOR((String)row.get("X_SWIFI_INSTL_FLOOR"));
            rowDTO.setX_SWIFI_INSTL_TY((String)row.get("X_SWIFI_INSTL_TY"));
            rowDTO.setX_SWIFI_INSTL_MBY((String)row.get("X_SWIFI_INSTL_MBY"));
            rowDTO.setX_SWIFI_SVC_SE((String)row.get("X_SWIFI_SVC_SE"));
            rowDTO.setX_SWIFI_CMCWR((String)row.get("X_SWIFI_CMCWR"));
            rowDTO.setX_SWIFI_CNSTC_YEAR((String)row.get("X_SWIFI_CNSTC_YEAR"));
            rowDTO.setX_SWIFI_INOUT_DOOR((String)row.get("X_SWIFI_INOUT_DOOR"));
            rowDTO.setX_SWIFI_REMARS3((String)row.get("X_SWIFI_REMARS3"));
            rowDTO.setLAT((String)row.get("LAT"));
            rowDTO.setLNT((String)row.get("LNT"));
            rowDTO.setWORK_DTTM((String)row.get("WORK_DTTM"));

            list.add(rowDTO);

            update(rowDTO);
        }

        return list;
    }

    public void update(RowDTO rowDTO){
        System.out.println(rowDTO);
        SecSql sql = new SecSql();

        sql.append("UPDATE wifi_information");
        sql.append("SET DIST = ?", rowDTO.getDIST());
//        sql.append("SET DIST = ?, X_SWIFI_MGR_NO = ?, X_SWIFI_WRDOFC = ?, X_SWIFI_MAIN_NM = ?," +
//                "X_SWIFI_ADRES1 = ?, X_SWIFI_ADRES2 = ?, X_SWIFI_INSTL_FLOOR = ?, X_SWIFI_INSTL_TY = ?," +
//                "X_SWIFI_INSTL_MBY = ?, X_SWIFI_SVC_SE = ?, X_SWIFI_CMCWR = ?, X_SWIFI_CNSTC_YEAR = ?," +
//                "X_SWIFI_INOUT_DOOR = ?, X_SWIFI_REMARS3 = ?, LAT = ?, LNT = ?, WORK_DTTM = ?",
//                rowDTO.getDIST(), rowDTO.getX_SWIFI_MGR_NO(), rowDTO.getX_SWIFI_WRDOFC(), rowDTO.getX_SWIFI_MAIN_NM(),
//                rowDTO.getX_SWIFI_ADRES1(), rowDTO.getX_SWIFI_ADRES2(), rowDTO.getX_SWIFI_INSTL_FLOOR(), rowDTO.getX_SWIFI_INSTL_TY(),
//                rowDTO.getX_SWIFI_INSTL_MBY(), rowDTO.getX_SWIFI_SVC_SE(), rowDTO.getX_SWIFI_CMCWR(), rowDTO.getX_SWIFI_CNSTC_YEAR(),
//                rowDTO.getX_SWIFI_INOUT_DOOR(), rowDTO.getX_SWIFI_REMARS3(), rowDTO.getLAT(), rowDTO.getLNT(), rowDTO.getWORK_DTTM());
        sql.append("WHERE ID = ?", rowDTO.getID());

        Mysql.update(sql);
    }

    public RowDTO select(long id){
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM wifi_information");
        sql.append("WHERE ID = ?", id);

        Map<String, Object> row = Mysql.selectRow(sql);
        RowDTO rowDTO = new RowDTO();
        rowDTO.setID((int)row.get("ID"));
        rowDTO.setDIST((double)row.get("DIST"));
        rowDTO.setX_SWIFI_MGR_NO((String)row.get("X_SWIFI_MGR_NO"));
        rowDTO.setX_SWIFI_WRDOFC((String)row.get("X_SWIFI_WRDOFC"));
        rowDTO.setX_SWIFI_MAIN_NM((String)row.get("X_SWIFI_MAIN_NM"));
        rowDTO.setX_SWIFI_ADRES1((String)row.get("X_SWIFI_ADRES1"));
        rowDTO.setX_SWIFI_ADRES2((String)row.get("X_SWIFI_ADRES2"));
        rowDTO.setX_SWIFI_INSTL_FLOOR((String)row.get("X_SWIFI_INSTL_FLOOR"));
        rowDTO.setX_SWIFI_INSTL_TY((String)row.get("X_SWIFI_INSTL_TY"));
        rowDTO.setX_SWIFI_INSTL_MBY((String)row.get("X_SWIFI_INSTL_MBY"));
        rowDTO.setX_SWIFI_SVC_SE((String)row.get("X_SWIFI_SVC_SE"));
        rowDTO.setX_SWIFI_CMCWR((String)row.get("X_SWIFI_CMCWR"));
        rowDTO.setX_SWIFI_CNSTC_YEAR((String)row.get("X_SWIFI_CNSTC_YEAR"));
        rowDTO.setX_SWIFI_INOUT_DOOR((String)row.get("X_SWIFI_INOUT_DOOR"));
        rowDTO.setX_SWIFI_REMARS3((String)row.get("X_SWIFI_REMARS3"));
        rowDTO.setLAT((String)row.get("LAT"));
        rowDTO.setLNT((String)row.get("LNT"));
        rowDTO.setWORK_DTTM((String)row.get("WORK_DTTM"));

        return rowDTO;
    }

    public List<RowDTO> selectAll(){
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM wifi_information");

        List<Map<String, Object>> rows = Mysql.selectRows(sql);

        List<RowDTO> list = new ArrayList<>();

        for (Map<String, Object> row : rows){
            RowDTO rowDTO = new RowDTO();
            rowDTO.setID((int)row.get("ID"));
            rowDTO.setDIST((double)row.get("DIST"));
            rowDTO.setX_SWIFI_MGR_NO((String)row.get("X_SWIFI_MGR_NO"));
            rowDTO.setX_SWIFI_WRDOFC((String)row.get("X_SWIFI_WRDOFC"));
            rowDTO.setX_SWIFI_MAIN_NM((String)row.get("X_SWIFI_MAIN_NM"));
            rowDTO.setX_SWIFI_ADRES1((String)row.get("X_SWIFI_ADRES1"));
            rowDTO.setX_SWIFI_ADRES2((String)row.get("X_SWIFI_ADRES2"));
            rowDTO.setX_SWIFI_INSTL_FLOOR((String)row.get("X_SWIFI_INSTL_FLOOR"));
            rowDTO.setX_SWIFI_INSTL_TY((String)row.get("X_SWIFI_INSTL_TY"));
            rowDTO.setX_SWIFI_INSTL_MBY((String)row.get("X_SWIFI_INSTL_MBY"));
            rowDTO.setX_SWIFI_SVC_SE((String)row.get("X_SWIFI_SVC_SE"));
            rowDTO.setX_SWIFI_CMCWR((String)row.get("X_SWIFI_CMCWR"));
            rowDTO.setX_SWIFI_CNSTC_YEAR((String)row.get("X_SWIFI_CNSTC_YEAR"));
            rowDTO.setX_SWIFI_INOUT_DOOR((String)row.get("X_SWIFI_INOUT_DOOR"));
            rowDTO.setX_SWIFI_REMARS3((String)row.get("X_SWIFI_REMARS3"));
            rowDTO.setLAT((String)row.get("LAT"));
            rowDTO.setLNT((String)row.get("LNT"));
            rowDTO.setWORK_DTTM((String)row.get("WORK_DTTM"));

            list.add(rowDTO);
        }

        return list;
    }

    public static void deleteAll(){
        SecSql sql = new SecSql();
        sql.append("DELETE FROM wifi_information");
        Mysql.delete(sql);
    }

    public int getCount() {
        int cnt = selectAll().size();
        return cnt;
    }
}
