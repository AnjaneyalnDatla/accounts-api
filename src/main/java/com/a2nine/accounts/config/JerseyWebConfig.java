package com.a2nine.accounts.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.a2nine.accounts.rest.AccountsController;
import com.a2nine.accounts.rest.ContactsController;
import com.a2nine.accounts.rest.ProductsController;
import com.a2nine.accounts.rest.TransactionsController;
import com.a2nine.accounts.util.CORSFilter;

@Configuration
@ApplicationPath("/accounts")
public class JerseyWebConfig extends ResourceConfig {

	public JerseyWebConfig() {
		register(ContactsController.class);
		register(TransactionsController.class);
		register(CORSFilter.class);
		register(AccountsController.class);
		register(ProductsController.class);
	}

}
