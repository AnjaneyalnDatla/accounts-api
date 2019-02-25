package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.a2nine.accounts.domain.model.Country;

public class CountryTest {

	@Test
	public void TestCountry() {
		assertEquals(Country.INDIA, Country.valueOfAbbreviation("IN"));
		assertEquals(Country.UNITED_STATES_OF_AMERICA, Country.valueOfAbbreviation("USA"));
		assertEquals(Country.UNKNOWN, Country.valueOfAbbreviation(null));
		assertEquals(Country.UNKNOWN, Country.valueOfAbbreviation(" "));
		
		assertEquals(Country.INDIA, Country.valueOfName("INDIA"));
		assertEquals(Country.UNITED_STATES_OF_AMERICA, Country.valueOfName("United States Of America"));
		assertEquals(Country.UNKNOWN, Country.valueOfAbbreviation(null));
		assertEquals(Country.UNKNOWN, Country.valueOfName(" "));
		
	}
	
}
