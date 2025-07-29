package org.calendar.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.api.model.dto.CalendarDTO;
import org.calendar.api.service.iface.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Operation(summary = "Get all days from calendar.")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<CalendarDTO> getAllDays() {

        return calendarService.getAllDaysFromCalendar();
    }

    @Operation(summary = "Get all days from calendar by year.")
    @GetMapping("/find-by-year")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<CalendarDTO> getDaysByYear(@RequestParam final Integer year) {

        return calendarService.getDaysByYear(year);
    }

    @Operation(summary = "Add a day from calendar.")
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CalendarDTO addDay(@RequestBody final CalendarDTO calendarDTO) {

        return calendarService.addDay(calendarDTO);
    }

    @Operation(summary = "Update a day from calendar.")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CalendarDTO updateDay(@RequestBody final CalendarDTO calendarDTO) {

        return calendarService.updateDay(calendarDTO);
    }

    @Operation(summary = "Delete a day from calendar.")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteDay(@RequestParam final Long id) {

        calendarService.deleteDay(id);
    }
}
