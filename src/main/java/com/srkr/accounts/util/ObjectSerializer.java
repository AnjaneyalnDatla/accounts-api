package com.srkr.accounts.util;


import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectSerializer {

	private ObjectSerializer() {
		throw new IllegalStateException("Utility class");
	}

	public static String toJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return mapper.writeValueAsString(object);
	}
	
	public static String toXMLString(Object object) throws JAXBException {
		Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		StringWriter writer = new StringWriter();
		marshaller.marshal(object, writer);
		return writer.toString();
	}

}

