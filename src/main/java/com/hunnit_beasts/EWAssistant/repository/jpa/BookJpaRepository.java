package com.hunnit_beasts.EWAssistant.repository.jpa;

import com.hunnit_beasts.EWAssistant.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookJpaRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByName(String name);

}
