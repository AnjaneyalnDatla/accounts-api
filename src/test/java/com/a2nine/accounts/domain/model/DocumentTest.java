package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.a2nine.accounts.domain.model.Document;

public class DocumentTest {

	@Test
	public void TestDocument() throws ParseException {
		Document document = new Document(new Long(1234555),	new Long(145673435), "SSN", "thisisthelink", new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()));
		assertNotNull(document);
		assertEquals(new Long(1234555), document.id());
		assertEquals(new Long(145673435), document.documentReferencerNumber());
		assertEquals("SSN",document.documentName());
		assertEquals("thisisthelink",document.documentLink());
		assertEquals(new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()),document.documentUploadedDate());
	}
}
