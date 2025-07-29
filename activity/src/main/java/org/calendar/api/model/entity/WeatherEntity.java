package org.calendar.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Entity
@Table(name = "weather")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private Double temperature;

    private Integer pressure;

    private Integer humidity;

    private LocalDateTime timestamp;
}
