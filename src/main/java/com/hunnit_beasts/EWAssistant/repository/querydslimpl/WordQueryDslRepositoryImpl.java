package com.hunnit_beasts.EWAssistant.repository.querydslimpl;

import com.hunnit_beasts.EWAssistant.dto.with.SelectedDTO;
import com.hunnit_beasts.EWAssistant.dto.with.WordDataDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordQuestionDTO;
import com.hunnit_beasts.EWAssistant.dto.word.WordSubmitDTO;
import com.hunnit_beasts.EWAssistant.entity.QBook;
import com.hunnit_beasts.EWAssistant.entity.QDay;
import com.hunnit_beasts.EWAssistant.entity.QWord;
import com.hunnit_beasts.EWAssistant.repository.querydsl.WordQueryDslRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class WordQueryDslRepositoryImpl implements WordQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<WordDataDTO> findByWords(List<SelectedDTO> dtoList) {

        QBook book = QBook.book;
        QDay day = QDay.day;
        QWord word = QWord.word1;

        return jpaQueryFactory
                .select(Projections.constructor(WordDataDTO.class, word.id, book.name, day.days, word.word, word.meaning))
                .from(book)
                .join(book.days, day)
                .join(day.words, word)
                .where(buildConditions(dtoList, book, day))
                .orderBy(book.id.asc())
                .fetch();
    }

    @Override
    public List<WordQuestionDTO> findIdAndWordByBookAndDays(List<SelectedDTO> dto) {
        QBook book = QBook.book;
        QDay day = QDay.day;
        QWord word = QWord.word1;

        return jpaQueryFactory
                .select(Projections.constructor(WordQuestionDTO.class, word.id, word.word))
                .from(book)
                .join(book.days, day)
                .join(day.words, word)
                .where(buildConditions(dto, book, day))
                .fetch();
    }

    public List<WordDataDTO> findAllByIds(List<WordSubmitDTO> dtos) {
        QBook book = QBook.book;
        QDay day = QDay.day;
        QWord word = QWord.word1;

        List<Long> ids = dtos.stream()
                .map(WordSubmitDTO::getId)
                .collect(Collectors.toList());

        Map<Long, Integer> idIndexMap = generateIdIndexMap(ids);

        List<WordDataDTO> result = jpaQueryFactory
                .select(Projections.constructor(WordDataDTO.class, word.id, book.name, day.days, word.word, word.meaning))
                .from(book)
                .join(book.days, day)
                .join(day.words, word)
                .where(word.id.in(ids))
                .fetch();

        result.sort((dto1, dto2) -> {
            int index1 = idIndexMap.get(dto1.getWordId());
            int index2 = idIndexMap.get(dto2.getWordId());
            return Integer.compare(index1, index2);
        });

        return result;
    }

    private Map<Long, Integer> generateIdIndexMap(List<Long> ids) {
        return IntStream.range(0, ids.size())
                .boxed()
                .collect(Collectors.toMap(ids::get, Function.identity()));
    }

    private BooleanExpression buildConditions(List<SelectedDTO> dtoList, QBook book, QDay day) {
        BooleanExpression predicate = null;
        for (SelectedDTO dto : dtoList) {
            BooleanExpression condition = book.name.eq(dto.getBook())
                    .and(day.days.eq(dto.getDays()));
            predicate = (predicate == null) ? condition : predicate.or(condition);
        }
        return predicate;
    }
}
