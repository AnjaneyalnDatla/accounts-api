package com.a2nine.accounts.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
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
	@Test
	public void givenValidIdAndOrgCode_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT").filter(document("{method-name}",
				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/account/id/1").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	
	@Test
	public void givenAccountTypes_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").filter(document("{method-name}",
				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/account/accountTypes").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	
	@Test
	public void givenValidJsonAccount_thenReturnHttp200() {
		String json = "{\"id\":17,\"name\":\"Bank Of Baroda\",\"description\":null,\"account_type\":{\"id\":1,\"name\":\"Bank\",\"accountCategory\":{\"id\":1,\"name\":\"Asset\",\"description\":null},\"description\":null},\"currentBalance\":2000325.0,\"dateUpdated\":null,\"isActive\":true,\"organisation\":{\"name\":\"DEFAULT\",\"code\":\"DEFAULT\"}}";
		given().baseUri(BASE_URI + port + "/").body(json).contentType(ContentType.JSON)
		.filter(document("{method-name}",
				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.post("/accounts/account").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	
}
