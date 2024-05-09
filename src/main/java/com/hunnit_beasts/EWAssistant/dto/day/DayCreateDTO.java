package com.hunnit_beasts.EWAssistant.dto.day;

import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.entity.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayCreateDTO {
    private Long days;
    private Book book;


    // 빌더 생성자
    public Day entityToCreateDTO(){
        return Day.builder()
                .days(days)
                .book(book)
                .build();
    }
}
