package com.hunnit_beasts.EWAssistant.dto.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WordGradingDTO {
    private Long wordId;
    private String word;
    private String meaning;
    private String userMeaning;
    private boolean isAnswer;
}
