package com.hunnit_beasts.EWAssistant.entity;

import com.hunnit_beasts.EWAssistant.dto.word.WordReadDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String word;

    @Column(length = 256, nullable = false)
    private String meaning;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    // 이하 필요 메서드

    // 빌더 생성자
    public WordReadDTO readDTOToEntity() {
        return WordReadDTO.builder()
                .word(word)
                .meaning(meaning)
                .build();
    }
}
