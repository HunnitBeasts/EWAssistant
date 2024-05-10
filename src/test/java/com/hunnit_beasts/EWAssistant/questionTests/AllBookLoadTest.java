package com.hunnit_beasts.EWAssistant.questionTests;

import com.hunnit_beasts.EWAssistant.dto.question.QuestionBookAndDaysDTO;
import com.hunnit_beasts.EWAssistant.dto.with.BookWithDaysDTO;
import com.hunnit_beasts.EWAssistant.service.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
public class AllBookLoadTest {

    private final QuestionService questionService;

    @Autowired
    public AllBookLoadTest(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Test
    void 모든책과_데이_갖고와보리기(){
        QuestionBookAndDaysDTO dto = questionService.readBookAndDays();
        List<BookWithDaysDTO> bookWithDaysDTOS = dto.getBookWithDaysDTOS();
        List<String> books = dto.getBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        for (int i = 0; i < bookWithDaysDTOS.size(); i++) {
            System.out.println(bookWithDaysDTOS.get(i));
        }
    }
}
