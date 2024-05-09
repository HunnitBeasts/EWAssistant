package com.hunnit_beasts.EWAssistant.dto.word;

import com.hunnit_beasts.EWAssistant.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordUpdateDTO {
    private Long id;
    private String word;
    private String meaning;


    // 빌더 생성자
    public Word entityToUpdateDTO(){
        return Word.builder()
                .id(id)
                .word(word)
                .meaning(meaning)
                .build();
    }
}
