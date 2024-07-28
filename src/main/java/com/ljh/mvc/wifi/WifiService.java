package com.ljh.mvc.wifi;

import com.ljh.container.Container;
import com.ljh.dto.RowDTO;

import java.util.List;

public class WifiService {
    private WifiRepository wifiRepository;

    public WifiService(){
        wifiRepository = Container.wifiRepository;
    }

    public int insert(List<RowDTO> rowList) {
        return wifiRepository.insert(rowList);
    }

    public void deleteAll() {
        wifiRepository.deleteAll();
    }

    public int getCount() {
        return wifiRepository.getCount();
    }

    public RowDTO select(long id) {
        return wifiRepository.select(id);
    }
}
