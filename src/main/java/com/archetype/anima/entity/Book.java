package com.archetype.anima.entity;

import lombok.Value;

@Value
public class Book {
    Isbn isbn;

    String title;

    String author;

    int price;

    public String getFormattedPrice() {
        return "￥" + String.format("%,d", price);
    }
}
