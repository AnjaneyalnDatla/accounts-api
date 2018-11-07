package com.srkr.accounts.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.srkr.accounts.rest.ContactsController;
import com.srkr.accounts.rest.HealthController;
import com.srkr.accounts.rest.TransactionsController;
import com.srkr.accounts.util.CORSFilter;

@Configuration
@ApplicationPath("/accounts")
public class JerseyWebConfig extends ResourceConfig {

	public JerseyWebConfig() {
		register(ContactsController.class);
		register(HealthController.class);
		register(TransactionsController.class);
		register(CORSFilter.class);

	}

}
