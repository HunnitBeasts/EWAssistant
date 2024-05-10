package com.hunnit_beasts.EWAssistant.dto.question;

import com.hunnit_beasts.EWAssistant.dto.with.BookWithDaysDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionBookAndDaysDTO {
    private final List<BookWithDaysDTO> bookWithDaysDTOS;
    private final List<String> books;
}
