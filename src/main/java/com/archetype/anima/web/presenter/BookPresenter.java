package com.archetype.anima.web.presenter;

import com.archetype.anima.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookPresenter {
    private final Book book;

    public String getIsbn() {
        return book.getIsbn().toString();
    }

    public String getAuthor() {
        return book.getAuthor();
    }

    public String getPrice() {
        return book.getFormattedPrice();
    }

    public String getTitle() {
        return book.getTitle();
    }
}
