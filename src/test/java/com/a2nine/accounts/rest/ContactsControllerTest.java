package com.a2nine.accounts.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
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


public class ContactsControllerTest {
	
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
	public void givenValidJsonContact_thenReturnHttp200() {
		String json = "{\"supplementalId\": \"06s11a1222\",\"isCompany\": \"true\",\"companyName\": \"ABC Constructions\",\"firstName\": \"Tony\",\"middleName\": \"Mark\",\"lastName\": \"Stark\",\"cellPhone\": \"1234567890\",\"homePhone\": \"3216549870\",\"officePhone\": \"7412589630\",\"faxNumber\": \"0123546987\",\"emailAddress\": \"test@test.com\",\"streetAddress\": \"Main St 123\",\"city\": \"Hyderabad\",\"state\": \"Telangana\",\"country\": \"India\",\"postalCode\": \"500082\",\"landMark\": \"Near Park avenue\",\"additionalComments\": \"Construction Seller\",\"idType\": \"Pan\",\"idNumber\": \"12032564100\",\"designation\": \"Supervisior\",\"organisation\":{\"name\":\"organisationname\",\"code\": \"organisation code\"}}";
		given().baseUri(BASE_URI + port + "/").body(json).contentType(ContentType.JSON)
		.filter(document("{method-name}",
				preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.post("/accounts/contacts").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	@Test
	public void givenValidOrgcode_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/contacts").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}
	@Test
	public void givenValidIdAndOrgcode_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT").queryParam("id", "1")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/contacts").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}

	@Test
	public void givenValidContact_thenReturnHttp200() {
		given().baseUri(BASE_URI + port + "/").queryParam("orgCode", "DEFAULT").queryParam("firstName", "Tony").queryParam("lastName", "Stark")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().scheme("http").host("localhost").removePort())))
				.get("/accounts/contacts/name").then().assertThat().statusCode(is(Response.Status.OK.getStatusCode()));
	}

}
