package com.ljh.mvc.wifi;

import com.google.gson.Gson;
import com.ljh.Rq;
import com.ljh.container.Container;
import com.ljh.dto.BookMarkDTO;
import com.ljh.dto.RowDTO;
import com.ljh.dto.TbPublicWifiInfoDTO;
import com.ljh.dto.WifiDTO;
import com.ljh.mvc.bookmark.BookMarkRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WifiController {
    private static String API_URL = "http://openapi.seoul.go.kr:8088/586f7a4973646c7736346d41634a50/json/TbPublicWifiInfo/";
    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static WifiService wifiService;
    private static BookMarkRepository bookMarkRepository;

    public WifiController(){
        wifiService = Container.wifiService;
        bookMarkRepository = Container.bookMarkRepository;
    }

    public WifiDTO wifiRequestAndResponse(int start, int end) throws IOException {
        WifiDTO wifiDTO = null;

        URL url = new URL(API_URL + start + "/" + end);

        Request.Builder builder = new Request.Builder().url(url).get();

        Response response = okHttpClient.newCall(builder.build()).execute();

        try {
            if (response.isSuccessful()){
                ResponseBody body = response.body();

                if (body != null){
                    String json = body.string();
                    Gson gson = new Gson();
                    TbPublicWifiInfoDTO cover = gson.fromJson(json, TbPublicWifiInfoDTO.class);
                    wifiDTO = cover.getTbPublicWifiInfo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wifiDTO;
    }

    public int loadWifiInformation() throws IOException {
        final int MAX_REQUEST = 1_000;
        int totCnt = wifiRequestAndResponse(1, 1).getList_total_count();
        int pageCnt = (totCnt + MAX_REQUEST) / MAX_REQUEST;

        if (totCnt == wifiService.getCount()){
            return totCnt;
        }

        int start = 1;
        int end = MAX_REQUEST;

        int cnt = 0;

        wifiService.deleteAll();
        bookMarkRepository.deleteAll();

        for (int i = 0; i < pageCnt; i++){
            WifiDTO wifiDTO = wifiRequestAndResponse(start, end);
            List<RowDTO> rowList = wifiDTO.getRow();
            cnt += wifiService.insert(rowList);
            start += MAX_REQUEST;
            end += MAX_REQUEST;
        }

        return cnt;
    }

    public void showMain(Rq rq) {
        rq.view("wifi/main");
    }

    public void showLoad(Rq rq) {
        rq.view("wifi/load");
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        RowDTO rowDTO = wifiService.select(id);

        rq.setAttr("row", rowDTO);
        rq.view("wifi/detail");
    }

    public void showHistory(Rq rq) {
        rq.view("wifi/history");
    }
}
