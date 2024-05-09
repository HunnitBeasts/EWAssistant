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
public class BookUpdateDTO {
    private Long id;
    private String name;


    // 빌더 생성자
    public Book entityToUpdateDTO(){
        return Book.builder()
                .id(id)
                .name(name)
                .build();
    }
}
