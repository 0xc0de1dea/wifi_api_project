package com.ljh.container;

import com.ljh.mvc.bookmark.BookMarkController;
import com.ljh.mvc.bookmark.BookMarkRepository;
import com.ljh.mvc.bookmark.BookMarkService;
import com.ljh.mvc.bookmarkgroup.BookMarkGroupController;
import com.ljh.mvc.bookmarkgroup.BookMarkGroupRepository;
import com.ljh.mvc.bookmarkgroup.BookMarkGroupService;
import com.ljh.mvc.history.HistoryController;
import com.ljh.mvc.history.HistoryRepository;
import com.ljh.mvc.history.HistoryService;
import com.ljh.mvc.wifi.WifiController;
import com.ljh.mvc.wifi.WifiRepository;
import com.ljh.mvc.wifi.WifiService;

public class Container {
    public static WifiRepository wifiRepository;
    public static HistoryRepository historyRepository;
    public static BookMarkRepository bookMarkRepository;
    public static BookMarkGroupRepository bookMarkGroupRepository;

    public static WifiService wifiService;
    public static HistoryService historyService;
    public static BookMarkService bookMarkService;
    public static BookMarkGroupService bookMarkGroupService;

    public static WifiController wifiController;
    public static HistoryController historyController;
    public static BookMarkController bookMarkController;
    public static BookMarkGroupController bookMarkGroupController;

    public static void init(){
        wifiRepository = new WifiRepository();
        historyRepository = new HistoryRepository();
        bookMarkRepository = new BookMarkRepository();
        bookMarkGroupRepository = new BookMarkGroupRepository();

        wifiService = new WifiService();
        historyService = new HistoryService();
        bookMarkService = new BookMarkService();
        bookMarkGroupService = new BookMarkGroupService();

        wifiController = new WifiController();
        historyController = new HistoryController();
        bookMarkController = new BookMarkController();
        bookMarkGroupController = new BookMarkGroupController();
    }
}
