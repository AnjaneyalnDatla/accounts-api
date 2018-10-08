package com.srkr.accounts.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/accounts")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

	}

}
