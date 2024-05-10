package com.hunnit_beasts.EWAssistant.dto.with;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookWithDaysDTO {
    private final String bookName;
    private final Long days;
}
