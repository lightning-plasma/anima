package com.archetype.anima.repository;

import com.archetype.anima.dto.BookDto;
import com.archetype.anima.entity.Book;
import com.archetype.anima.entity.Isbn;
import com.archetype.anima.error.InsertException;
import com.archetype.anima.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<BookDto> bookDtoRowMapper =
        (rs, rowNum) -> new BookDto(
            rs.getString("isbn"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getObject("price", Integer.class),
            rs.getObject("created_at", LocalDateTime.class),
            rs.getObject("updated_at", LocalDateTime.class)
        );

    public List<Book> findAll() {
        List<BookDto> bookDtoList = jdbcTemplate.query(
            "SELECT isbn, title, author, price, created_at, updated_at FROM book", bookDtoRowMapper
        );

        return bookDtoList.stream().map(
            it -> new Book(new Isbn(it.getIsbn()), it.getTitle(), it.getAuthor(), it.getPrice())
        ).collect(Collectors.toUnmodifiableList());
    }

    public Book findBy(Isbn isbn) {
        // queryForObjectはno hitの場合にEmptyResultDataAccessExceptionを投げるのでListで処理する
        List<BookDto> dtoList = jdbcTemplate.query(
            "SELECT isbn, title, author, price, created_at, updated_at FROM book WHERE isbn=?",
            bookDtoRowMapper,
            isbn.getValue()
        );

        if (dtoList.size() != 1) {
            throw new NotFoundException("Book is not found. isbn=" + isbn);
        }

        BookDto dto = dtoList.get(0);

        return new Book(new Isbn(dto.getIsbn()), dto.getTitle(), dto.getAuthor(), dto.getPrice());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void insert(Book book) {
        int result;
        try {
            result = jdbcTemplate.update(
                "INSERT INTO book (isbn, title, author, price, created_at) VALUES(?, ?, ?, ?, ?)",
                book.getIsbn().getValue(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                LocalDateTime.now()
            );
        } catch (DataAccessException ex) {
            throw new InsertException(ex.getMessage());
        }

        if (result != 1) {
            throw new InsertException("multiple line inserted");
        }
    }
}
