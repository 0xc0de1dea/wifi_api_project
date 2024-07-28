package com.ljh.mvc.bookmarkgroup;

import com.ljh.dto.BookMarkGroupDTO;
import com.ljh.util.Mysql;
import com.ljh.util.SecSql;

import java.awt.print.Book;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookMarkGroupRepository {
    public BookMarkGroupDTO select(long id){
        BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM bookmark_group");
        sql.append("WHERE ID = ?", id);

        Map<String, Object> row = Mysql.selectRow(sql);
        bookMarkGroupDTO.setID((int)row.get("ID"));
        bookMarkGroupDTO.setNAME((String)row.get("NAME"));
        bookMarkGroupDTO.setORDER_NO((int)row.get("ORDER_NO"));
        bookMarkGroupDTO.setREGISTER_DATETIME((String)row.get("REGISTER_DATETIME"));
        bookMarkGroupDTO.setEDIT_DATETIME((String)row.get("EDIT_DATETIME"));

        return bookMarkGroupDTO;
    }

    public List<BookMarkGroupDTO> selectAll(){
        List<BookMarkGroupDTO> bookMarkGroupList = new ArrayList<>();

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM bookmark_group");

        List<Map<String, Object>> rows = Mysql.selectRows(sql);

        for (Map<String, Object> row : rows) {
            BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();
            bookMarkGroupDTO.setID((int)row.get("ID"));
            bookMarkGroupDTO.setNAME((String)row.get("NAME"));
            bookMarkGroupDTO.setORDER_NO((int)row.get("ORDER_NO"));
            bookMarkGroupDTO.setREGISTER_DATETIME((String)row.get("REGISTER_DATETIME"));
            bookMarkGroupDTO.setEDIT_DATETIME((String)row.get("EDIT_DATETIME"));

            bookMarkGroupList.add(bookMarkGroupDTO);
        }

        return bookMarkGroupList;
    }

    public void insert(BookMarkGroupDTO bookmarkGroupDTO){
        SecSql sql = new SecSql();
        sql.append("INSERT INTO bookmark_group");
        sql.append("(NAME, ORDER_NO, REGISTER_DATETIME)");
        sql.append("VALUES(?, ?, ?)",
                bookmarkGroupDTO.getNAME(),
                bookmarkGroupDTO.getORDER_NO(),
                bookmarkGroupDTO.getREGISTER_DATETIME());

        Mysql.insert(sql);
    }

    public void update(BookMarkGroupDTO bookmarkGroupDTO){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SecSql sql = new SecSql();
        sql.append("UPDATE bookmark_group");
        sql.append("SET NAME = ?, ORDER_NO = ?, EDIT_DATETIME = ?",
                bookmarkGroupDTO.getNAME(),
                bookmarkGroupDTO.getORDER_NO(),
                bookmarkGroupDTO.getEDIT_DATETIME());
        sql.append("WHERE ID = ?",
                bookmarkGroupDTO.getID());

        Mysql.update(sql);
    }

    public void delete(long id){
        SecSql sql = new SecSql();
        sql.append("DELETE FROM bookmark_group");
        sql.append("WHERE ID = ?", id);

        Mysql.delete(sql);
    }
}
