package org.calendar.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

    private String city;

    private Double temperature;

    private Integer pressure;

    private Integer humidity;

    private LocalDateTime timestamp;
}
