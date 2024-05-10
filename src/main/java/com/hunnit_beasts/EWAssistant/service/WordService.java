package com.hunnit_beasts.EWAssistant.service;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.*;
import com.hunnit_beasts.EWAssistant.service.modules.CrudService;

import java.util.List;

public interface WordService extends CrudService<WordCreateDTO, WordReadDTO, WordUpdateDTO, Long> {
    List<WordDataDTO> readWordData(List<SelectedDTO> dtos);
    List<WordDataDTO> readWords(List<WordSubmitDTO> dtos);
}
