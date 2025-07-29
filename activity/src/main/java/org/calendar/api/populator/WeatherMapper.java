package org.calendar.api.populator;

import org.calendar.api.config.GlobalMapperConfig;
import org.calendar.api.model.dto.WeatherDTO;
import org.calendar.api.model.entity.WeatherEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = GlobalMapperConfig.class)
public interface WeatherMapper {

    WeatherDTO toDTO(final WeatherEntity weatherEntity);

    List<WeatherDTO> toDTOList(final List<WeatherEntity> weatherEntities);

    WeatherEntity toEntity(final WeatherDTO calendarDTO);
}
