package com.hunnit_beasts.EWAssistant.dto.book;

import com.hunnit_beasts.EWAssistant.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateDTO {
    private String name;


    // 빌더 생성자
    public Book entityToCreateDTO(){
        return Book.builder()
                .name(name)
                .build();
    }
}
