package com.archetype.anima.web.converter;

import com.archetype.anima.entity.Book;
import com.archetype.anima.entity.Isbn;
import com.archetype.anima.web.form.BookForm;

public class BookConverter {
    private BookConverter() {}

    public static Book convert(BookForm form) {
        return new Book(
            new Isbn(form.getIsbn()),
            form.getTitle(),
            form.getAuthor(),
            Integer.parseInt(form.getPrice())
        );
    }
}
