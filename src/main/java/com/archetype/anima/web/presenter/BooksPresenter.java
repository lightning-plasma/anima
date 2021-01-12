package com.archetype.anima.web.presenter;

import com.archetype.anima.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BooksPresenter {
    private final List<Book> books;
}
