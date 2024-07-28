package com.ljh.servlet;

import com.ljh.Rq;
import com.ljh.container.Container;
import com.ljh.mvc.bookmark.BookMarkController;
import com.ljh.mvc.bookmarkgroup.BookMarkGroupController;
import com.ljh.mvc.history.HistoryController;
import com.ljh.mvc.wifi.WifiController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/wifi/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        WifiController wifiController = Container.wifiController;
        HistoryController historyController = Container.historyController;
        BookMarkGroupController bookMarkGroupController = Container.bookMarkGroupController;
        BookMarkController bookMarkController = Container.bookMarkController;

        switch (rq.getRouteMethod()){
            case "GET" -> {
                switch (rq.getActionPath()){
                    case "/wifi/main" -> wifiController.showMain(rq);
                    case "/wifi/load" -> wifiController.showLoad(rq);
                    case "/wifi/detail" -> wifiController.showDetail(rq);
                    case "/wifi/history" -> wifiController.showHistory(rq);
                    case "/wifi/bookmarkgroup/list" -> bookMarkGroupController.showList(rq);
                    case "/wifi/bookmarkgroup/insert" -> bookMarkGroupController.showInsert(rq);
                    case "/wifi/bookmarkgroup/update" -> bookMarkGroupController.showUpdate(rq);
                    case "/wifi/bookmarkgroup/delete" -> bookMarkGroupController.showDelete(rq);
                    case "/wifi/bookmark/list" -> bookMarkController.showList(rq);
                }
            }
            case "POST" -> {
                switch (rq.getActionPath()){
                    case "/wifi/bookmarkgroup/insert" -> bookMarkGroupController.doInsert(rq);
                    case "/wifi/bookmarkgroup/update" -> bookMarkGroupController.doUpdate(rq);
                    case "/wifi/bookmark/add" -> bookMarkController.doAdd(rq);
                }
            }
            case "DELETE" -> {
                switch (rq.getActionPath()){
                    case "/wifi/history/delete" -> historyController.doDelete(rq);
                    case "/wifi/bookmarkgroup/delete" -> bookMarkGroupController.doDelete(rq);
                    case "/wifi/bookmark/delete" -> bookMarkController.doDelete(rq);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
