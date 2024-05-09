package com.hunnit_beasts.EWAssistant.dto.day;

import com.hunnit_beasts.EWAssistant.entity.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayUpdateDTO {
    private Long id;
    private Long days;


    // 빌더 생성자
    public Day entityToUpdateDTO(){
        return Day.builder()
                .id(id)
                .days(days)
                .build();
    }
}
