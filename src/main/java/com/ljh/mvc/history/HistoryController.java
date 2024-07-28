package com.ljh.mvc.history;

import com.ljh.Rq;
import com.ljh.container.Container;
import com.ljh.dto.HistoryDTO;
import com.ljh.mvc.wifi.WifiService;

public class HistoryController {
    private static HistoryService historyService;

    public HistoryController(){
        historyService = Container.historyService;
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        HistoryDTO historyDTO = historyService.select(id);

        if (historyDTO == null) {
            rq.historyBack("해당 히스토리가 존재하지 않습니다.");
            return;
        }

        historyService.delete(id);

        rq.replace("/wifi/history", "%d번 히스토리가 삭제되었습니다.".formatted(id));
    }
}
