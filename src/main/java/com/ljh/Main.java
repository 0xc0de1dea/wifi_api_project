package com.ljh;

import com.ljh.mvc.wifi.WifiController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        App.init();
        WifiController wifiController = new WifiController();
        wifiController.loadWifiInformation();
    }
}