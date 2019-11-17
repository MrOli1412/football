package com.pl.football.backend.model;

public enum TransferType {
    DEFINITYWNY("D"),
    CZASOWY("C"),
    WOLNY_ZAWODNIK("Z"),
    WYCHOWANEK("W");

    String value;
    TransferType(String w) {
        this.value = w;
    }

    public String getValue() {
        return value;
    }
}
