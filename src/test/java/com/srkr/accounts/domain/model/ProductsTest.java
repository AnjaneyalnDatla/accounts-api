package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProductsTest {

	@Test 
	public void TestProducts() {
		Products products = new Products(new Long(975637), "Oatnut");
		
		assertNotNull(products);
		assertEquals(new Long(975637), products.getId());
		assertEquals("Oatnut", products.getName());
	}
}
