package com.hunnit_beasts.EWAssistant.repository.querydsl;

import com.hunnit_beasts.EWAssistant.dto.with.BookWithDaysDTO;

import java.util.List;

public interface DayQueryDslRepository {
    List<BookWithDaysDTO> findAllBookAndDays();
}
