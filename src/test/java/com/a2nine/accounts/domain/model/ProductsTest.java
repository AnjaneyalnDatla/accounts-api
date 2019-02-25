package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.Products;

public class ProductsTest {

	@Test
	public void TestProducts() {
		Products products = new Products(new Long(975637), "Oatnut", new Organisation("DEFAULT", "DEFAULT"));

		assertNotNull(products);
		assertEquals(new Long(975637), products.getId());
		assertEquals("Oatnut", products.getName());
	}
}
