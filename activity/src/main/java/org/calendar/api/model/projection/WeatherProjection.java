package org.calendar.api.model.projection;

import java.time.LocalDateTime;

public interface WeatherProjection {

    String getCity();

    Double getTemperature();

    Integer getPressure();

    Integer getHumidity();

    LocalDateTime getTimestamp();
}
