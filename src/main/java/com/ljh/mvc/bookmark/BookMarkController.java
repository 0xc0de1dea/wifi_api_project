package com.ljh.mvc.bookmark;

import com.ljh.Rq;
import com.ljh.container.Container;
import com.ljh.dto.BookMarkDTO;
import com.ljh.dto.BookMarkGroupDTO;
import com.ljh.mvc.bookmarkgroup.BookMarkGroupService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookMarkController {
    private BookMarkService bookMarkService;
    private BookMarkGroupService bookMarkGroupService;

    public BookMarkController(){
        bookMarkService = Container.bookMarkService;
        bookMarkGroupService = Container.bookMarkGroupService;
    }

    public void doAdd(Rq rq) {
        long groupID = rq.getIntParam("bookmark_group_name", 0);

        if (groupID == 0){
            rq.historyBack("올바른 선택을 해주세요.");
            return;
        }

        long wifiID = rq.getIntParam("wifi", 0);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        BookMarkDTO bookMarkDTO = new BookMarkDTO();
        bookMarkDTO.setGROUP_ID((int)groupID);
        bookMarkDTO.setWIFI_ID((int)wifiID);
        bookMarkDTO.setREGISTER_DATETIME(dateFormat.format(date));

        bookMarkService.insert(bookMarkDTO);

        rq.replace("/wifi/bookmark/list", "북마크가 추가 되었습니다.");
    }

    public void showList(Rq rq) {
        rq.view("wifi/bookmark-list");
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        BookMarkDTO bookMarkDTO = bookMarkService.select(id);

        if (bookMarkDTO == null) {
            rq.historyBack("해당 북마크가 존재하지 않습니다.");
            return;
        }

        bookMarkService.delete(id);

        rq.replace("/wifi/bookmark/list", "%d번 북마크가 삭제되었습니다.".formatted(id));
    }
}
