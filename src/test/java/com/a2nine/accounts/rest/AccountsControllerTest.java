package com.a2nine.accounts.rest;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.builder.RequestSpecBuilder;

import javax.ws.rs.core.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountsControllerTest {

	private static final String BASE_URI = "http://localhost:";

	@LocalServerPort
	private int port;

	private RequestSpecification spec;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Before
	public void setUp() {
		this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void givenValidOrgcode_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/account").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}

}
