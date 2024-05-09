package com.hunnit_beasts.EWAssistant.entity;

import com.hunnit_beasts.EWAssistant.dto.day.DayReadDTO;
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
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long days;

    // 연관관계 매핑 수정
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Word> words = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // 빌더 생성자
    public DayReadDTO readDTOToEntity() {
        return DayReadDTO.builder()
                .id(id)
                .days(days)
                .build();
    }
}
