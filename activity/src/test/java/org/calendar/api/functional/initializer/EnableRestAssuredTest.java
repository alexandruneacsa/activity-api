package org.calendar.api.functional.initializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.calendar.api.initializer.TestContainersInitialization;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RestAssuredTestExtension.class)
@ContextConfiguration(initializers = {TestContainersInitialization.class})
@Tag(value = "functional")
public @interface EnableRestAssuredTest {
}

