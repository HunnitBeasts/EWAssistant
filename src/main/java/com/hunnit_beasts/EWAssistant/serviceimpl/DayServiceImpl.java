package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.day.DayCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.day.DayReadDTO;
import com.hunnit_beasts.EWAssistant.dto.day.DayUpdateDTO;
import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.entity.Day;
import com.hunnit_beasts.EWAssistant.enums.ErrorCode;
import com.hunnit_beasts.EWAssistant.repository.jpa.DayJpaRepository;
import com.hunnit_beasts.EWAssistant.service.DayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DayServiceImpl implements DayService {
    private final DayJpaRepository dayJpaRepository;
    @Override
    public DayReadDTO create(DayCreateDTO dto) {
        return dayJpaRepository.save(dto.entityToCreateDTO())
                .readDTOToEntity();
    }

    @Override
    public DayReadDTO read(Long id) {
        return dayJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()))
                .readDTOToEntity();
    }

    @Override
    public DayReadDTO update(DayUpdateDTO dto) {
        return dayJpaRepository.save(dto.entityToUpdateDTO())
                .readDTOToEntity();
    }

    @Override
    public Long delete(Long id) {
        dayJpaRepository.deleteById(id);
        return id;
    }

    @Override
    public boolean isDay(Long day, Book book) {
        return dayJpaRepository.existsByDaysAndBook(day,book);
    }

    @Override
    public Day findId(Long day, Book book) {
        return dayJpaRepository.findByDaysAndBook(day, book);
    }
}
