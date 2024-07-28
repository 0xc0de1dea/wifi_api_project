package com.ljh;

import com.ljh.container.Container;
import com.ljh.util.Mysql;

public class App {
    public static void init(){
        Mysql.setDBInfo("localhost", "wifiID", "star816438*", "wifiDB");
        Mysql.setDevMode(false);

        Container.init();
    }
}
