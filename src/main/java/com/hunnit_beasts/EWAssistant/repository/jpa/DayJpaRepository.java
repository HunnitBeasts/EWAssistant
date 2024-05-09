package com.hunnit_beasts.EWAssistant.repository.jpa;

import com.hunnit_beasts.EWAssistant.entity.Book;
import com.hunnit_beasts.EWAssistant.entity.Day;
import org.springframework.data.repository.CrudRepository;

public interface DayJpaRepository extends CrudRepository<Day, Long> {

    boolean existsByDaysAndBook(Long days, Book book);
    Day findByDaysAndBook(Long days, Book book);

}
