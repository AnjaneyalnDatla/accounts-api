package com.srkr.accounts.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectDeserializer {

	private ObjectDeserializer() {
		throw new IllegalStateException("Utility class");
	}

	public static Object toObject(String jsonData, Class<?> type) throws IOException {
		return new ObjectMapper().readValue(jsonData, type);
	}
}
