package org.calendar.api.populator;

import org.calendar.api.config.GlobalMapperConfig;
import org.calendar.api.model.dto.CalendarDTO;
import org.calendar.api.model.entity.CalendarEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = GlobalMapperConfig.class)
public interface CalendarMapper {

	CalendarDTO toDTO(final CalendarEntity calendarEntity);

	List<CalendarDTO> toDTOList(final List<CalendarEntity> calendarEntities);

	CalendarEntity toEntity(final CalendarDTO calendarDTO);
}
