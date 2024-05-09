package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.*;
import com.hunnit_beasts.EWAssistant.entity.Word;
import com.hunnit_beasts.EWAssistant.enums.ErrorCode;
import com.hunnit_beasts.EWAssistant.repository.jpa.WordJpaRepository;
import com.hunnit_beasts.EWAssistant.repository.querydsl.WordQueryDslRepository;
import com.hunnit_beasts.EWAssistant.service.WordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class WordServiceImpl implements WordService {

    private final WordJpaRepository wordJpaRepository;
    private final WordQueryDslRepository wordQueryDslRepository;

    @Override
    public WordReadDTO create(WordCreateDTO dto) {
        Word entity = dto.entityToCreateDTO();
        Word savedEntity = wordJpaRepository.save(entity);
        return savedEntity.readDTOToEntity();
    }

    @Override
    public WordReadDTO read(Long id) {
        Word entity = wordJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
        return entity.readDTOToEntity();
    }

    @Override
    public WordReadDTO update(WordUpdateDTO dto) {
        Word entity = dto.entityToUpdateDTO();
        Word savedEntity = wordJpaRepository.save(entity);
        return savedEntity.readDTOToEntity();
    }

    @Override
    public Long delete(Long id) {
        wordJpaRepository.deleteById(id);
        return id;
    }

    @Override
    public List<WordDataDTO> readWordData(List<SelectedDTO> dtos) {
        return wordQueryDslRepository.findByWords(dtos);
    }

    @Override
    public List<WordQuestionDTO> createQuestion(List<SelectedDTO> dtos, Integer count) {
        List<WordQuestionDTO> questions = wordQueryDslRepository.findIdAndWordByBookAndDays(dtos);
        return selectRandomQuestions(questions, count);
    }

    @Override
    public List<WordDataDTO> readWords(List<WordSubmitDTO> dtos) {
        return wordQueryDslRepository.findAllByIds(dtos);
    }

    @Override
    public List<WordGradingDTO> grading(List<WordSubmitDTO> dtos) {
        List<WordDataDTO> answers = readWords(dtos);
        return gradeAnswers(dtos, answers);
    }

    private List<WordQuestionDTO> selectRandomQuestions(List<WordQuestionDTO> questions, Integer count) {
        int[] randomIndices = generateRandomIndices(count, questions.size());
        List<WordQuestionDTO> result = new ArrayList<>();
        for (int index : randomIndices)
            result.add(questions.get(index));
        return result;
    }

    private int[] generateRandomIndices(int length, int max) {
        int[] indices = new int[length];
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(max);
            } while (set.contains(randomNumber));
            indices[i] = randomNumber;
            set.add(randomNumber);
        }
        return indices;
    }

    private List<WordGradingDTO> gradeAnswers(List<WordSubmitDTO> dtos, List<WordDataDTO> answers) {
        List<WordGradingDTO> result = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            WordSubmitDTO submitDTO = dtos.get(i);
            WordDataDTO answerDTO = answers.get(i);
            boolean isCorrect = isAnswerCorrect(submitDTO.getMeaning(), answerDTO.getMeaning());
            result.add(answerDTO.DataDTOToGradingDTO(isCorrect, submitDTO.getMeaning()));
        }
        return result;
    }

    private boolean isAnswerCorrect(String yourAnswer, String correctAnswer) {
        if (yourAnswer == null || yourAnswer.isEmpty())
            return false;
        else
            return correctAnswer.contains(yourAnswer);
    }
}
