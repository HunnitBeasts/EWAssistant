package com.hunnit_beasts.EWAssistant.repository.querydsl;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;

import java.util.List;

public interface WordQueryDslRepository {
    List<WordDataDTO> findByWords(List<SelectedDTO> dto);
    List<WordQuestionDTO> findIdAndWordByBookAndDays(List<SelectedDTO> dto);
    List<WordDataDTO> findAllByIds(List<WordSubmitDTO> dtos);
}
