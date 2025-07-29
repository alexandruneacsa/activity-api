package org.calendar.api.functional.endpoint;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.calendar.api.functional.initializer.EnableRestAssuredTest;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@EnableRestAssuredTest
class CalendarTest {
	
	@Test
	void givenCalendarEndpoint_whenGetAll_thenStatusIs200() {
		
		RestAssured.given().accept(ContentType.JSON).when().get("/calendar")
			.then().assertThat().statusCode(200)
			.and().contentType(ContentType.JSON)
			.and().time(lessThan(2000L));
	}
	
	@Test
	void givenCalendarEndpoint_whenGetAll_thenStatusIs200AndResponseIsNotNull() {
		
		RestAssured.given().accept(ContentType.JSON).when().get("/calendar")
			.then().assertThat().statusCode(200)
			.and().contentType(ContentType.JSON)
			.and().time(lessThan(2000L))
			.and().body("$", notNullValue());
	}
	
	@Test
	void givenYearParam_whenGetDaysByYear_thenStatusIs200AndValidResponse() {
		
		RestAssured.given().accept(ContentType.JSON).queryParam("year", 2025).when().get("/calendar/find-by-year")
			.then().assertThat().statusCode(200)
			.and().contentType(ContentType.JSON)
			.and().time(lessThan(2000L));
	}
	
	@Test
	void givenInvalidYearParam_whenGetDaysByYear_thenStatusIs200AndEmptyResponse() {
		
		RestAssured.given().accept(ContentType.JSON).queryParam("year", 2001).when().get("/calendar/find-by-year")
			.then().assertThat().statusCode(200)
			.and().contentType(ContentType.JSON)
			.and().time(lessThan(2000L))
			.and().body("$", empty());
	}
	
	
	@Test
	void givenInvalidCalendarEndpoint_whenGetAll_thenStatusIs404() {
		
		RestAssured.given().accept(ContentType.JSON).when().get("/calendar-invalid")
			.then().assertThat().statusCode(500);
	}
	
	@Test
	void givenMissingYearParam_whenGetDaysByYear_thenStatusIs400() {
		
		RestAssured.given().accept(ContentType.JSON).when().get("/calendar/find-by-year")
			.then().assertThat().statusCode(500);
	}
	
	@Test
	void givenValidCalendarDTO_whenPostDay_thenStatusIs200AndResponseIsValid() {
		
		var requestBody = """
			{
			    "day": 10,
			    "month": 7,
			    "year": 2025
			}
			""";
		
		RestAssured.given().contentType(ContentType.JSON).body(requestBody).when().post("/calendar")
			.then().statusCode(200).contentType(ContentType.JSON).body("day", equalTo(10)).body("month", equalTo(7))
			 .body("year", equalTo(2025));
	}
	
	@Test
	void givenCalendarEntry_whenPutDay_thenStatusIs200AndUpdated() {
		
		var postBody = """
			{
			    "day": 15,
			    "month": 8,
			    "year": 2025
			}
			""";
		
		var id = ((Number) RestAssured.given().contentType(ContentType.JSON).body(postBody).when().post("/calendar")
			.then().statusCode(200).extract().path("id")).longValue();
		
		var putBody = """
			{
			    "id": %d,
			    "day": 15,
			    "month": 8,
			    "year": 2025,
			    "description": "Updated holiday"
			}
			""".formatted(id);
		
		RestAssured.given().contentType(ContentType.JSON).body(putBody).when().put("/calendar")
			.then().statusCode(200);
	}
	
	
	@Test
	void givenCalendarEntry_whenDeleteDay_thenStatusIs200() {
		
		var postBody = """
			{
			    "day": 20,
			    "month": 12,
			    "year": 2025
			}
			""";
		
		var id = ((Number) RestAssured.given().contentType(ContentType.JSON).body(postBody).when().post("/calendar")
			.then().statusCode(200).extract().path("id")).longValue();
		
		RestAssured.given().queryParam("id", id).when().delete("/calendar")
			.then().statusCode(200);
	}
}

