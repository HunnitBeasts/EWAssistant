package com.hunnit_beasts.EWAssistant.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDay is a Querydsl query type for Day
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDay extends EntityPathBase<Day> {

    private static final long serialVersionUID = -386996781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDay day = new QDay("day");

    public final QBook book;

    public final NumberPath<Long> days = createNumber("days", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Word, QWord> words = this.<Word, QWord>createList("words", Word.class, QWord.class, PathInits.DIRECT2);

    public QDay(String variable) {
        this(Day.class, forVariable(variable), INITS);
    }

    public QDay(Path<? extends Day> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDay(PathMetadata metadata, PathInits inits) {
        this(Day.class, metadata, inits);
    }

    public QDay(Class<? extends Day> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

