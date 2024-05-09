package com.hunnit_beasts.EWAssistant.service;

import com.hunnit_beasts.EWAssistant.dto.day.DayCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.day.DayReadDTO;
import com.hunnit_beasts.EWAssistant.dto.day.DayUpdateDTO;
import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.entity.Day;
import com.hunnit_beasts.EWAssistant.service.modules.CrudService;

public interface DayService extends CrudService<DayCreateDTO, DayReadDTO, DayUpdateDTO, Long> {

    boolean isDay(Long day, Book book);
    Day findId(Long day, Book book);
}
