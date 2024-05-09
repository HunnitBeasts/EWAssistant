package com.hunnit_beasts.EWAssistant.dto.with;

import com.hunnit_beasts.EWAssistant.dto.word.WordGradingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class WordDataDTO {
    private Long wordId;
    private String book;
    private Long day;
    private String word;
    private String meaning;

    public WordGradingDTO DataDTOToGradingDTO(boolean isAnswer, String userMeaning){
        return WordGradingDTO.builder()
                .wordId(wordId)
                .word(word)
                .meaning(meaning)
                .userMeaning(userMeaning)
                .isAnswer(isAnswer)
                .build();
    }
}
