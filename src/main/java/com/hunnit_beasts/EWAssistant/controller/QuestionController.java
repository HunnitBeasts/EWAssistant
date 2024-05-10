package com.hunnit_beasts.EWAssistant.controller;

import com.hunnit_beasts.EWAssistant.dto.question.QuestionBookAndDaysDTO;
import com.hunnit_beasts.EWAssistant.dto.question.QuestionCreateDTO;
import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordGradingDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;
import com.hunnit_beasts.EWAssistant.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("all-book-and-days")
    public ResponseEntity<QuestionBookAndDaysDTO> allBookAndDays(){
        return new ResponseEntity<>(questionService.readBookAndDays(), HttpStatus.OK);
    }

    @PostMapping("create-test")
    public ResponseEntity<List<WordQuestionDTO>> allBookAndDays(@RequestBody QuestionCreateDTO dto){
        return new ResponseEntity<>(questionService.createQuestion(dto.getDtos(),dto.getCount()), HttpStatus.OK);
    }

    @PostMapping("test-grading")
    public ResponseEntity<List<WordGradingDTO>> grading(@RequestBody List<WordSubmitDTO> dto){
        return new ResponseEntity<>(questionService.grading(dto), HttpStatus.OK);
    }


}
