package com.ljh.mvc.bookmark;

import com.ljh.dto.BookMarkDTO;
import com.ljh.util.Mysql;
import com.ljh.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMarkRepository {
    public BookMarkDTO select(long id){
        BookMarkDTO bookMarkDTO = new BookMarkDTO();

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM bookmark");
        sql.append("WHERE ID = ?", id);

        Map<String, Object> row = Mysql.selectRow(sql);

        bookMarkDTO.setID((int)row.get("ID"));
        bookMarkDTO.setGROUP_ID((int)row.get("GROUP_ID"));
        bookMarkDTO.setWIFI_ID((int)row.get("WIFI_ID"));
        bookMarkDTO.setREGISTER_DATETIME((String)row.get("REGISTER_DATETIME"));

        return bookMarkDTO;
    }

    public List<BookMarkDTO> selectAll(){
        List<BookMarkDTO> bookMarkDTOList = new ArrayList<>();

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM bookmark");

        List<Map<String, Object>> rows = Mysql.selectRows(sql);

        for (Map<String, Object> row : rows) {
            BookMarkDTO bookMarkDTO = new BookMarkDTO();
            bookMarkDTO.setID((int)row.get("ID"));
            bookMarkDTO.setGROUP_ID((int)row.get("GROUP_ID"));
            bookMarkDTO.setWIFI_ID((int)row.get("WIFI_ID"));
            bookMarkDTO.setREGISTER_DATETIME((String)row.get("REGISTER_DATETIME"));

            bookMarkDTOList.add(bookMarkDTO);
        }

        return bookMarkDTOList;
    }

    public void insert(BookMarkDTO bookMarkDTO){
        SecSql sql = new SecSql();
        sql.append("INSERT INTO bookmark");
        sql.append("(GROUP_ID, WIFI_ID, REGISTER_DATETIME)");
        sql.append("values (?, ?, ?)",
                bookMarkDTO.getGROUP_ID(),
                bookMarkDTO.getWIFI_ID(),
                bookMarkDTO.getREGISTER_DATETIME());

        Mysql.insert(sql);
    }

    public void delete(long id){
        SecSql sql = new SecSql();
        sql.append("DELETE FROM bookmark");
        sql.append("WHERE ID = ?", id);

        Mysql.delete(sql);
    }

    public void deleteAll(){
        SecSql sql = new SecSql();
        sql.append("DELETE FROM bookmark");

        Mysql.delete(sql);
    }
}
