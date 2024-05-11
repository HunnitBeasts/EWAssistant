package com.hunnit_beasts.EWAssistant.dto.word;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WordSubmitDTO {
    private final Long wordId;
    private final String meaning;
}
