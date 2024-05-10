package com.hunnit_beasts.EWAssistant.repository.querydslimpl;

import com.hunnit_beasts.EWAssistant.dto.with.BookWithDaysDTO;
import com.hunnit_beasts.EWAssistant.entity.QBook;
import com.hunnit_beasts.EWAssistant.entity.QDay;
import com.hunnit_beasts.EWAssistant.repository.querydsl.DayQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DayQueryDslRepositoryImpl implements DayQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<BookWithDaysDTO> findAllBookAndDays() {
        QBook book = QBook.book;
        QDay day = QDay.day;

        return jpaQueryFactory
                .select(Projections.constructor(BookWithDaysDTO.class, book.name, day.days))
                .from(book)
                .join(book.days, day)
                .fetch();
    }
}
