package org.calendar.api.generated;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CalendarApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {

        assertThat(context).isNotNull();
    }
}
