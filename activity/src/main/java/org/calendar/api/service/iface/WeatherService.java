package org.calendar.api.service.iface;

import org.calendar.api.model.dto.QueryFilter;
import org.calendar.api.model.dto.WeatherDTO;
import org.calendar.api.model.projection.WeatherProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WeatherService {

    List<WeatherDTO> getAllWeatherData();

    List<WeatherProjection> findByCity(final String city);

    Page<WeatherProjection> getWeatherByFilters(final Pageable pageable, final List<QueryFilter> queryFilters);

    void deleteAllWeatherData();
}
