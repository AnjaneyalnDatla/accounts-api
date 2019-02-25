package com.a2nine.accounts.domain.model;

import java.util.HashMap;
import java.util.Map;

public enum State {

    ANDHRA_PRADESH("ANDHRA PRADESH", "AP"), TELANGANA("TELANGANA", "TG"), UNKNOWN(
    "Unknown", "");

    private String name;
    private String abbreviation;
    private static final Map<String, State> STATES_BY_ABBR = new HashMap<>();

    static {
        for (State state : values()) {
            STATES_BY_ABBR.put(state.abbreviation(), state);
        }
    }

    State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String abbreviation() {
        return abbreviation;
    }

    public static State valueOfAbbreviation(final String abbr) {
        final State state = STATES_BY_ABBR.get(abbr);
        if (state != null) {
            return state;
        } else {
            return UNKNOWN;
        }
    }
    
    public static State valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return State.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}