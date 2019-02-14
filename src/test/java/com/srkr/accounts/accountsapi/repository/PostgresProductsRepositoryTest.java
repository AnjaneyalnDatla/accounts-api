package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.Products;
import com.srkr.accounts.domain.model.repositories.PostgresProductsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresProductsRepositoryTest {

	@Autowired
	PostgresProductsRepository productsRepository;
	
	private List<Products> productsList;
	private List<Products> productsDeleteList = new ArrayList<>();
	@Before
	public void setUp() {
		Products products = new Products();
		products.setDateupdated(new Date());
		products.setName("New Product");
		productsList.add(products);
	}
	
	@Test
	public void findAll() {
		List<Products> products = productsRepository.findAll();
		assertNotNull(products);
	}
	
	@Test
	public void save() {
		productsList.forEach((product) -> {
			productsDeleteList.add(this.productsRepository.save(product));
		});
		assertTrue(productsDeleteList.size() > 0);
	}
	/*
	@After
	public void delete() {
		productsDeleteList.forEach(product -> {
			this.productsRepository.delete(product);
		});
	}*/
	
}
