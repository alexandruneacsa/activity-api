package org.calendar.api.generated;

import org.calendar.api.CalendarApplication;
import org.springframework.boot.SpringApplication;

public class TestCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.from(CalendarApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
