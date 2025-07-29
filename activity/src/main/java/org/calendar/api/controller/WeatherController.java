package org.calendar.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.api.model.dto.QueryFilter;
import org.calendar.api.model.dto.WeatherDTO;
import org.calendar.api.model.projection.WeatherProjection;
import org.calendar.api.service.iface.WeatherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(summary = "Get all weather data.")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<WeatherDTO> getAllWeatherData() {

        return weatherService.getAllWeatherData();
    }

    @Operation(summary = "Get weather data by city name.")
    @GetMapping("weather-from")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<WeatherProjection> getAllWeatherDataByCityName(@RequestParam final String cityName) {

        return weatherService.findByCity(cityName);
    }

    @Operation(summary = "Get weather by filters")
    @PostMapping("/filters")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public Page<WeatherProjection> getWeatherByFilters(
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) @PageableDefault(size = 25) final Pageable pageable,
            @RequestBody final List<QueryFilter> queryFilters) {

        return weatherService.getWeatherByFilters(pageable, queryFilters);
    }
}
