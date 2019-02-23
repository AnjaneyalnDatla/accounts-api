package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.Organisation;
import com.srkr.accounts.domain.model.Products;
import com.srkr.accounts.usecases.FindAndSaveProducts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindAndSaveProductsTest {

	@Autowired
	private FindAndSaveProducts findAndSaveProducts;
	private List<Products> productsList;
	private long id;

	@Before
	public void setUp() {
		Random rand = new Random();
		this.productsList = new ArrayList<>();
		id = rand.nextLong();
		Products products = new Products(id, "Oatnut", new Organisation("DEFAULT", "DEFAULT"));
		productsList.add(products);
	}
	
	@Test
	public void saveProduct() {
		productsList.forEach( product ->{
			assertNotNull(findAndSaveProducts.saveProduct(product));
		});
	}
	
	@Test
	public void findAllProducts() {
		assertNotNull(findAndSaveProducts.findAllProducts());
	}

}
