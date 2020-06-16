package com.pl.football.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum TransferType {
    DEFINITYWNY("D"),
    CZASOWY("C"),
    WOLNY_ZAWODNIK("Z"),
    WYCHOWANEK("W");

    String value;

    TransferType(String w) {
        this.value = w;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TransferType fromValue(String value) {
        for (TransferType t : TransferType.values()) {
            if (t.value.equals(value)) return t;
        }
        throw new IllegalArgumentException();
    }
}
