package com.hunnit_beasts.EWAssistant.serviceimpl;

import com.hunnit_beasts.EWAssistant.dto.question.QuestionBookAndDaysDTO;
import com.hunnit_beasts.EWAssistant.dto.with.BookWithDaysDTO;
import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordGradingDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;
import com.hunnit_beasts.EWAssistant.repository.querydsl.DayQueryDslRepository;
import com.hunnit_beasts.EWAssistant.repository.querydsl.WordQueryDslRepository;
import com.hunnit_beasts.EWAssistant.service.QuestionService;
import com.hunnit_beasts.EWAssistant.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final DayQueryDslRepository dayQueryDslRepository;
    private final WordQueryDslRepository wordQueryDslRepository;
    private final WordService wordService;
    @Override
    public QuestionBookAndDaysDTO readBookAndDays() {
        List<BookWithDaysDTO> bookWithDaysDTOS = dayQueryDslRepository.findAllBookAndDays();
        Set<String> uniqueBookNames = new LinkedHashSet<>();
        for (BookWithDaysDTO dto : bookWithDaysDTOS)
            uniqueBookNames.add(dto.getBookName());
        return new QuestionBookAndDaysDTO(bookWithDaysDTOS, new ArrayList<>(uniqueBookNames));
    }

    @Override
    public List<WordQuestionDTO> createQuestion(List<SelectedDTO> dtos, Integer count) {
        List<WordQuestionDTO> questions = wordQueryDslRepository.findIdAndWordByBookAndDays(dtos);
        return selectRandomQuestions(questions, count);
    }
    @Override
    public List<WordGradingDTO> grading(List<WordSubmitDTO> dtos) {
        List<WordDataDTO> answers = wordService.readWords(dtos);
        return gradeAnswers(dtos, answers);
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

}
