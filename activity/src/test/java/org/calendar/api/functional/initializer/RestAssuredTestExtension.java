package org.calendar.api.functional.initializer;

import io.restassured.RestAssured;
import org.calendar.api.CalendarApplication;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestAssuredTestExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {

        RestAssured.baseURI = "http://localhost:9090";
        SpringApplication.run(CalendarApplication.class);
    }
}

