package com.hunnit_beasts.EWAssistant.repository.jpa;

import com.hunnit_beasts.EWAssistant.entity.Word;
import org.springframework.data.repository.CrudRepository;

public interface WordJpaRepository extends CrudRepository<Word,Long> {
}
