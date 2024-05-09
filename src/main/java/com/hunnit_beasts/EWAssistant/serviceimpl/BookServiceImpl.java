package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.book.BookCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.book.BookReadDTO;
import com.hunnit_beasts.EWAssistant.dto.book.BookUpdateDTO;
import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.enums.ErrorCode;
import com.hunnit_beasts.EWAssistant.repository.jpa.BookJpaRepository;
import com.hunnit_beasts.EWAssistant.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookJpaRepository bookJpaRepository;

    @Override
    public BookReadDTO create(BookCreateDTO dto) {
        return bookJpaRepository.save(dto.entityToCreateDTO())
                .createDTOToEntity();
    }

    @Override
    public BookReadDTO read(Long id) {
        return bookJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()))
                .createDTOToEntity();
    }

    @Override
    public BookReadDTO update(BookUpdateDTO dto) {
        return bookJpaRepository.save(dto.entityToUpdateDTO())
                .createDTOToEntity();
    }

    @Override
    public Long delete(Long id) {
        bookJpaRepository.deleteById(id);
        return id;
    }

    @Override
    public Book getBook(String name) {
        return bookJpaRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
    }
}
