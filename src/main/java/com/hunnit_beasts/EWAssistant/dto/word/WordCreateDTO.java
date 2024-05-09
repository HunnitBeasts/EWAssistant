package com.hunnit_beasts.EWAssistant.dto.word;

import com.hunnit_beasts.EWAssistant.entity.Day;
import com.hunnit_beasts.EWAssistant.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordCreateDTO {
    private String word;
    private String meaning;
    private Day day;


    // 빌더 생성자
    public Word entityToCreateDTO(){
        return Word.builder()
                .word(word)
                .meaning(meaning)
                .day(day)
                .build();
    }
}
