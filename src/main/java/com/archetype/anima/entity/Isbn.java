package com.archetype.anima.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Isbn {
    private final String value;

    public Isbn(String _value) {
        // かんたんvalidation
        if (_value == null || _value.length() != 13) {
            throw new IllegalArgumentException();
        }
        this.value = _value;
    }

    @Override
    public String toString() {
        return value;
    }
}
