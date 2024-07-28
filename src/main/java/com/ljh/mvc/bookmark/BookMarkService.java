package com.ljh.mvc.bookmark;

import com.ljh.container.Container;
import com.ljh.dto.BookMarkDTO;

import java.util.List;

public class BookMarkService {
    private BookMarkRepository bookMarkRepository;

    public BookMarkService(){
        bookMarkRepository = Container.bookMarkRepository;
    }

    public void insert(BookMarkDTO bookMarkDTO) {
        bookMarkRepository.insert(bookMarkDTO);
    }

    public BookMarkDTO select(long id) {
        return bookMarkRepository.select(id);
    }

    public void delete(long id) {
        bookMarkRepository.delete(id);
    }
}
