package com.hunnit_beasts.EWAssistant.dto.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordReadDTO {
    private String word;
    private String meaning;
}
