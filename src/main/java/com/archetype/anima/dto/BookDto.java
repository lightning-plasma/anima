package com.archetype.anima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookDto {
    private String isbn;

    private String title;

    private String author;

    private int price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
