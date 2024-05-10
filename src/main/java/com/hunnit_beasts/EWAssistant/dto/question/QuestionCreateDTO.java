package com.hunnit_beasts.EWAssistant.dto.question;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionCreateDTO {
    private final List<SelectedDTO> dtos;
    private final Integer count;
}
