package com.pl.football.backend.model;

import java.util.HashMap;
import java.util.Map;

public enum Position {
    COACH("TRENER"),
    SECOND_COACH("DRUGI TRENER"),
    MASSEUR("MASARZYSTA"),
    MEDICAL_CARER("OPIEKUN MEDYCZNY"),
    TEAM_MANAGER("KIEROWNIK DRUŻYNY"),
    ODER("INNA");
    private String function;

    Position(String function) {
        this.function = function;
    }

    private static final Map<String, Position> lookup = new HashMap<>();

    public String getFunction() {
        return function;
    }

    static {
        for (Position pos : Position.values()) {
            lookup.put(pos.getFunction(), pos);
        }
    }

    public static Position get(String function) {
        return lookup.get(function);
    }
}
