package com.a2nine.accounts.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import javax.ws.rs.core.Response;

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
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductsControllerTest {
	
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
	public void givenValidJsonProduct_thenReturnHttp200() {
		String json = "{\"id\":\"1\",\"name\":\"Product naame\",\"organisation\":{	\"name\":\"Organisation Name\",	\"code\":\"DEFAULT\"}}";
		given().baseUri(BASE_URI + port + "/").body(json).contentType(ContentType.JSON)
//		.filter(document("{method-name}",
//				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.post("/accounts/products").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	
	@Test
	public void givenValidOrgCode_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT")
//		.filter(document("{method-name}",
//				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/products").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	

}
