package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.a2nine.accounts.domain.model.LineItem;
import com.a2nine.accounts.domain.model.Products;

public class LineItemTest {
	@Test
	public void TestLineItem() {
		LineItem lineItem = new LineItem(new Long(98912), 126, 1223, new Products("Dolo"), "Medical", 123, 25.00, 125.00, new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()));
		
		assertNotNull(lineItem);
		assertNotNull(lineItem.products());
		
		assertEquals(new Long(98912), lineItem.id());
		assertEquals(new Integer(126), lineItem.transaction_number());
		assertEquals(new Integer(1223), lineItem.line_item_number());
		assertEquals("Dolo", lineItem.products().getName());
		assertEquals("Medical", lineItem.name());
		assertEquals(new Integer(123), lineItem.quantity());
		assertEquals(new Double(25.00), lineItem.price());
		assertEquals(new Double(125.00), lineItem.amount());
		assertEquals(new Date((new GregorianCalendar(2019,2,9)).getTimeInMillis()), lineItem.dateupdated());
	}
	
}
