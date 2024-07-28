package com.ljh.mvc.history;

import com.ljh.container.Container;
import com.ljh.dto.HistoryDTO;
import com.ljh.mvc.wifi.WifiRepository;

public class HistoryService {
    private HistoryRepository historyRepository;

    public HistoryService(){
        historyRepository = Container.historyRepository;
    }

    public HistoryDTO select(long id) {
        return historyRepository.select(id);
    }

    public void delete(long id) {
        historyRepository.delete(id);
    }
}
