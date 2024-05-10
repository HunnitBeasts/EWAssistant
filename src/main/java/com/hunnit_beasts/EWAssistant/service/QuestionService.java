package com.hunnit_beasts.EWAssistant.service;

import com.hunnit_beasts.EWAssistant.dto.question.QuestionBookAndDaysDTO;
import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordGradingDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;

import java.util.List;

public interface QuestionService {
    QuestionBookAndDaysDTO readBookAndDays();
    List<WordQuestionDTO> createQuestion(List<SelectedDTO> dtos, Integer count);
    List<WordGradingDTO> grading(List<WordSubmitDTO> dtos);
}
