package com.srkr.accounts.domain.model;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertionConcern {
	protected AssertionConcern() {
	}

	protected void assertArgumentLength(String aString, int aMinimum, int aMaximum, String aMessage) {
		int length = aString.trim().length();
		if ((length < aMinimum) || (length > aMaximum)) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	protected void assertArgumentNotEmpty(String aString, String aMessage) {
		if ((aString == null) || (aString.trim().isEmpty())) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	protected void assertArgumentTrue(boolean aBoolean, String aMessage) {
		if (!aBoolean) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	protected void assertRegexTrue(String anExpression, String regex, String aMessage) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(anExpression);
		if (!matcher.find()) {
			throw new IllegalArgumentException(aMessage);
		}

	}

	protected void assertArgumentLength(String aString, int aMaximum, String aMessage) {
		int length = aString.trim().length();
		if (length > aMaximum) {
			throw new IllegalArgumentException(aMessage);
		}
	}
	
	protected void assertCollectionNotEmpty(@SuppressWarnings("rawtypes") Collection aCollection, String aMessage) {
		if (aCollection.isEmpty()) {
			throw new IllegalArgumentException(aMessage);
		}
	}
}