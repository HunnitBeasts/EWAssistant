package com.hunnit_beasts.EWAssistant.service;

import com.hunnit_beasts.EWAssistant.dto.book.BookCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.book.BookReadDTO;
import com.hunnit_beasts.EWAssistant.dto.book.BookUpdateDTO;
import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.service.modules.CrudService;

public interface BookService extends CrudService<BookCreateDTO, BookReadDTO, BookUpdateDTO, Long> {

    Book getBook(String name);

}
