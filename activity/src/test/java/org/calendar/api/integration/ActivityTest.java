package org.calendar.api.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.calendar.api.initializer.CustomSpringContext;
import org.calendar.api.model.dto.QueryFilter;
import org.calendar.api.util.PageCustomImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.calendar.api.model.enums.FilterType.EQUALS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CustomSpringContext
class ActivityTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTemplatesByFiltersReturnSuccess() throws Exception {

        final var city = "Bucharest";

        final var nameFilter = new QueryFilter("city", EQUALS.getValue(), city);

        final var activityFilterList = List.of(nameFilter);

        final MvcResult result = mvc.perform(post("/weather/filters").content(objectMapper.writeValueAsString(activityFilterList))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        Assertions.assertThat(result).isNotNull();

        final PageCustomImpl<WeatherRecord> actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertNotNull(actual);
        assertEquals(6, actual.totalElements());
    }

    record WeatherRecord(Long id,
                         String city,
                         Double temperature,
                         Integer pressure,
                         Integer humidity,
                         LocalDateTime timestamp) {
    }
}
