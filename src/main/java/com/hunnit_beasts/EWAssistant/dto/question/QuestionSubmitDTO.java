package com.hunnit_beasts.EWAssistant.dto.question;

import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionSubmitDTO {

    private List<WordSubmitDTO> SubmitList;
}
