package org.calendar.api.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.calendar.api.initializer.CustomSpringContext;
import org.calendar.api.model.dto.CalendarDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@CustomSpringContext
@Sql(scripts = "classpath:database/calendar_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CalendarTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static final String CALENDAR_URL = "/calendar";

    @Test
    @DisplayName("Get all days from calendar positive test.")
    void shouldReturnAllDays_whenRequestIsSuccessful() throws Exception {

        final var result = mvc.perform(get(CALENDAR_URL)).andExpect(status().isOk()).andReturn();

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Add a day in calendar positive test.")
    void shouldAddDays_whenRequestIsSuccessful() throws Exception {

        final var request = CalendarDTO.builder().withDay(26).withMonth(9).withYear(2025).build();

        final var resultAfterInsert = mvc.perform(
                        post(CALENDAR_URL).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final var insertedDay = objectMapper.readValue(resultAfterInsert.getResponse().getContentAsString(),
                CalendarDTO.class);

        final var allDaysResult = mvc.perform(get(CALENDAR_URL)).andExpect(status().isOk()).andReturn();

        final List<CalendarDTO> allDays = objectMapper.readValue(allDaysResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        var element = allDays.stream()
                .filter(calendarDTO -> calendarDTO.getId().equals(insertedDay.getId()))
                .findFirst().orElseThrow();

        Assertions.assertThat(element).isNotNull();

        mvc.perform(delete(CALENDAR_URL).param("id", insertedDay.getId().toString()))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("Update a day from calendar positive test.")
    void shouldUpdateDays_whenRequestIsSuccessful() throws Exception {

        final var request = CalendarDTO.builder().withId(1L).withDay(4).withMonth(1).withYear(2025).build();

        final var resultAfterUpdate = mvc.perform(
                        put(CALENDAR_URL).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final var updatedDay = objectMapper.readValue(resultAfterUpdate.getResponse().getContentAsString(),
                CalendarDTO.class);

        final var allDaysResult = mvc.perform(get(CALENDAR_URL)).andExpect(status().isOk()).andReturn();

        final List<CalendarDTO> allDays = objectMapper.readValue(allDaysResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        var element = allDays.stream()
                .filter(calendarDTO -> calendarDTO.getId().equals(updatedDay.getId()))
                .findFirst().orElseThrow();

        Assertions.assertThat(element).isNotNull();
        Assertions.assertThat(element.getDay()).isEqualTo(4);
    }

    @Test
    @DisplayName("Update a day from calendar negative test.")
    void shouldReturnBadRequest_whenUpdatingDaysWithInvalidData() throws Exception {

        final var request = CalendarDTO.builder().withDay(24).withMonth(1).withYear(2025).build();

        final var result = mvc.perform(
                        put(CALENDAR_URL).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        var exception = result.getResolvedException();

        assert exception != null;
        assertEquals("Record to update is null or has no id.", exception.getMessage());
    }

    @Test
    @DisplayName("Delete a day from calendar negative test.")
    void shouldDeleteBankHolidays_whenRequestIsSuccessful() throws Exception {

        final var request = CalendarDTO.builder().withDay(31).withMonth(2).withYear(2025).build();

        final var resultAfterInsert = mvc.perform(
                        post(CALENDAR_URL).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final var insertedDay = objectMapper.readValue(resultAfterInsert.getResponse().getContentAsString(),
                CalendarDTO.class);

        final MvcResult result = mvc.perform(delete(CALENDAR_URL).param("id", insertedDay.getId().toString()))
                .andExpect(status().isOk()).andReturn();

        final MvcResult afterDelete = mvc.perform(get(CALENDAR_URL)).andExpect(status().isOk()).andReturn();

        final var actualAfterDelete = objectMapper.readValue(afterDelete.getResponse().getContentAsString(),
                new TypeReference<List<?>>() {
                });

        Assertions.assertThat(result).isNotNull();
        assertFalse(actualAfterDelete.contains(insertedDay));
    }
}
