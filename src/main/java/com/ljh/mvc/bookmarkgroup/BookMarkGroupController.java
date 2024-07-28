package com.ljh.mvc.bookmarkgroup;

import com.ljh.Rq;
import com.ljh.container.Container;
import com.ljh.dto.BookMarkGroupDTO;
import com.ljh.dto.HistoryDTO;
import com.ljh.dto.RowDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookMarkGroupController {
    private BookMarkGroupService bookMarkGroupService;

    public BookMarkGroupController(){
        bookMarkGroupService = Container.bookMarkGroupService;
    }

    public List<BookMarkGroupDTO> selectAll() {
        return bookMarkGroupService.selectAll();
    }

    public void showList(Rq rq) {
        rq.view("wifi/bookmark-group");
    }

    public void doInsert(Rq rq) {
        String name = rq.getParam("bookmark_name", "");

        if (name.trim().isEmpty()){
            rq.historyBack("북마크 그룹 이름을 입력해주세요.");
            return;
        }

        String orderNo = rq.getParam("bookmark_order_no", "");

        if (orderNo.trim().isEmpty()){
            rq.historyBack("순번을 입력해주세요.");
            return;
        }

        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();
            bookMarkGroupDTO.setNAME(name);
            bookMarkGroupDTO.setORDER_NO(Integer.parseInt(orderNo));
            bookMarkGroupDTO.setREGISTER_DATETIME(dateFormat.format(date));

            bookMarkGroupService.insert(bookMarkGroupDTO);

            rq.replace("/wifi/bookmarkgroup/list", "북마크 그룹이 추가 되었습니다.");
        } catch (NumberFormatException e){
            rq.historyBack("숫자를 입력해주세요.");
            return;
        }
    }

    public void showInsert(Rq rq) {
        rq.view("wifi/bookmark-group-insert");
    }

    public void showUpdate(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupService.select(id);

        rq.setAttr("id", bookMarkGroupDTO);
        rq.view("wifi/bookmark-group-update");
    }

    public void doUpdate(Rq rq) {
        String name = rq.getParam("name", "");

        if (name.trim().isEmpty()){
            rq.historyBack("북마크 이름을 입력해주세요.");
            return;
        }

        String orderNo = rq.getParam("order", "");

        if (orderNo.trim().isEmpty()){
            rq.historyBack("순번을 입력해주세요.");
            return;
        }

        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            long id = rq.getLongPathValueByIndex(1, 0);

            BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupService.select(id);
            bookMarkGroupDTO.setNAME(name);
            bookMarkGroupDTO.setORDER_NO(Integer.parseInt(orderNo));
            bookMarkGroupDTO.setEDIT_DATETIME(dateFormat.format(date));

            bookMarkGroupService.update(bookMarkGroupDTO);

            rq.replace("/wifi/bookmarkgroup/list", "북마크가 수정 되었습니다.");
        } catch (NumberFormatException e){
            rq.historyBack("숫자를 입력해주세요.");
            return;
        }
    }

    public void showDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupService.select(id);

        rq.setAttr("id", bookMarkGroupDTO);
        rq.view("wifi/bookmark-group-delete");
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupService.select(id);

        if (bookMarkGroupDTO == null) {
            rq.historyBack("해당 북마크그룹이 존재하지 않습니다.");
            return;
        }

        bookMarkGroupService.delete(id);

        rq.replace("/wifi/bookmarkgroup/list", "%d번 북마크그룹이 삭제되었습니다.".formatted(id));
    }
}
