package com.ljh.mvc.bookmarkgroup;

import com.ljh.container.Container;
import com.ljh.dto.BookMarkGroupDTO;

import java.util.List;

public class BookMarkGroupService {
    private BookMarkGroupRepository bookMarkGroupRepository;

    public BookMarkGroupService(){
        bookMarkGroupRepository = Container.bookMarkGroupRepository;
    }

    public List<BookMarkGroupDTO> selectAll() {
        return bookMarkGroupRepository.selectAll();
    }

    public void insert(BookMarkGroupDTO bookMarkGroupDTO) {
        bookMarkGroupRepository.insert(bookMarkGroupDTO);
    }

    public BookMarkGroupDTO select(long id) {
        return bookMarkGroupRepository.select(id);
    }

    public void update(BookMarkGroupDTO bookMarkGroupDTO) {
        bookMarkGroupRepository.update(bookMarkGroupDTO);
    }

    public void delete(long id) {
        bookMarkGroupRepository.delete(id);
    }
}
