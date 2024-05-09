package com.hunnit_beasts.EWAssistant.dto.day;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayReadDTO {
    private Long id;
    private Long days;
}
