package com.srkr.accounts.domain.model;

import java.util.HashMap;
import java.util.Map;

public enum Country {

    INDIA("India", "IN"), UNITED_STATES_OF_AMERICA("United States Of America", "USA"), UNKNOWN(
    "Unknown", "");

    private String name;
    private String abbreviation;
    private static final Map<String, Country> COUNTRY_BY_ABBR = new HashMap<>();

    static {
        for (Country country : values()) {
        	COUNTRY_BY_ABBR.put(country.abbreviation(), country);
        }
    }

    Country(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String abbreviation() {
        return abbreviation;
    }

    public static Country valueOfAbbreviation(final String abbr) {
        final Country country = COUNTRY_BY_ABBR.get(abbr);
        if (country != null) {
            return country;
        } else {
            return UNKNOWN;
        }
    }
    
    public static Country valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return Country.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
