package org.calendar.api.unit;

import org.calendar.api.model.dto.WeatherDTO;
import org.calendar.api.model.entity.WeatherEntity;
import org.calendar.api.populator.WeatherMapper;
import org.calendar.api.repository.WeatherRepository;
import org.calendar.api.service.implementation.WeatherServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMapper weatherMapper;

    @InjectMocks
    private WeatherServiceImplementation weatherServiceImplementation;

    @Test
    @DisplayName("Get all weather data.")
    public void getAllWeatherData() {

        // Given
        var mockEntities = List.of(new WeatherEntity(), new WeatherEntity());
        var mockDTOs = List.of(new WeatherDTO(), new WeatherDTO());


        Mockito.when(weatherRepository.findAll()).thenReturn(mockEntities);
        Mockito.when(weatherMapper.toDTOList(mockEntities)).thenReturn(mockDTOs);

        // When
        var result = weatherServiceImplementation.getAllWeatherData();

        // Then
        assertEquals(mockDTOs.size(), result.size());
        verify(weatherRepository).findAll();
        verify(weatherMapper).toDTOList(mockEntities);
    }
}
