package org.calendar.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.api.service.iface.ActivityService;
import org.calendar.api.service.iface.CalendarService;
import org.calendar.api.service.iface.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support")
public class SupportController {

    private final CalendarService calendarService;
    private final WeatherService weatherService;
    private final ActivityService activityService;

    @Operation(summary = "Delete all days from calendar.")
    @DeleteMapping("/clean-days")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void cleanDays() {

        calendarService.deleteAllDays();
    }

    @Operation(summary = "Delete all weather data.")
    @DeleteMapping("/clean-weather")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void cleanWeatherData() {

        weatherService.deleteAllWeatherData();
    }

    @Operation(summary = "Delete all activities.")
    @DeleteMapping("/clean-activities")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void cleanActivities() {

        activityService.deleteAllActivities();
    }
}
