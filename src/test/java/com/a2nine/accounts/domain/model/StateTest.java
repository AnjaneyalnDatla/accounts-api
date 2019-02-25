package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.a2nine.accounts.domain.model.State;

public class StateTest {
	
	@Test
	public void TestState() {
		assertEquals(State.ANDHRA_PRADESH, State.valueOfAbbreviation("AP"));
		assertEquals(State.TELANGANA, State.valueOfAbbreviation("TG"));
		assertEquals(State.UNKNOWN, State.valueOfAbbreviation(null));
		assertEquals(State.UNKNOWN, State.valueOfAbbreviation(" "));
		
		assertEquals(State.ANDHRA_PRADESH, State.valueOfName("Andhra Pradesh"));
		assertEquals(State.TELANGANA, State.valueOfName("Telangana"));
		assertEquals(State.UNKNOWN, State.valueOfAbbreviation(null));
		assertEquals(State.UNKNOWN, State.valueOfName(" "));
	}
}
