package com.ljh.mvc.history;

import com.ljh.dto.HistoryDTO;
import com.ljh.util.Mysql;
import com.ljh.util.SecSql;

import java.text.SimpleDateFormat;
import java.util.*;

public class HistoryRepository {
    public void insert(String lat, String lnt) {
        Date today = new Date();
        Locale currentLocal = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocal);

        SecSql sql = new SecSql();
        sql.append("INSERT INTO wifi_history");
        sql.append("(LAT, LNT, SEARCH_DATETIME)");
        sql.append("VALUES (?, ?, ?)", lat, lnt, formatter.format(today)).toString();

        Mysql.insert(sql);
    }

    public List<HistoryDTO> selectAll(){
        List<HistoryDTO> list = new ArrayList<>();

        SecSql sql = new SecSql();
        sql.append("SELECT * FROM wifi_history");
        sql.append("ORDER BY ID DESC");

        List<Map<String, Object>> rows = Mysql.selectRows(sql);

        for (Map<String, Object> row : rows) {
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setID((int)row.get("ID"));
            historyDTO.setLAT((String)row.get("LAT"));
            historyDTO.setLNT((String)row.get("LNT"));
            historyDTO.setSEARCH_DATETIME((String)row.get("SEARCH_DATETIME"));

            list.add(historyDTO);
        }

        return list;
    }

    public HistoryDTO select(long id){
        HistoryDTO historyDTO = new HistoryDTO();

        SecSql sql = new SecSql();
        sql.append("SELECT * FROM wifi_history WHERE ID = ?", id);

        Map<String, Object> row = Mysql.selectRow(sql);

        historyDTO.setID((int)row.get("ID"));
        historyDTO.setLAT((String)row.get("LAT"));
        historyDTO.setLNT((String)row.get("LNT"));
        historyDTO.setSEARCH_DATETIME((String)row.get("SEARCH_DATETIME"));

        return historyDTO;
    }

    public void delete(long id){
        SecSql sql = new SecSql();
        sql.append("DELETE FROM wifi_history WHERE ID = ?", id);

        Mysql.delete(sql);
    }
}
