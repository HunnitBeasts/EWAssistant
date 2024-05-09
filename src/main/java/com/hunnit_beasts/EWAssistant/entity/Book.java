package com.hunnit_beasts.EWAssistant.entity;

import com.hunnit_beasts.EWAssistant.dto.book.BookReadDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true, nullable = false)
    private String name;

    // 연관관계 매핑 수정
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<>();

    // 이하 필요 메서드

    // 빌더 생성자
    public BookReadDTO createDTOToEntity() {
        return BookReadDTO.builder()
                .name(name)
                .build();
    }
}
