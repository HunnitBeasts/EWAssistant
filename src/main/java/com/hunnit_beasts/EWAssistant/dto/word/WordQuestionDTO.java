package com.hunnit_beasts.EWAssistant.dto.word;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WordQuestionDTO {
    private final Long id;
    private final String word;
}
