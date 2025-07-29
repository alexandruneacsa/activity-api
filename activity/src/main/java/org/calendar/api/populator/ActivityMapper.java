package org.calendar.api.populator;

import org.calendar.api.config.GlobalMapperConfig;
import org.calendar.api.model.dto.ActivityDTO;
import org.calendar.api.model.entity.ActivityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = GlobalMapperConfig.class)
public interface ActivityMapper {

    ActivityDTO toDTO(final ActivityEntity activityEntity);

    List<ActivityDTO> toDTOList(final List<ActivityEntity> activityEntities);

    ActivityEntity toEntity(final ActivityDTO activityDTO);
}

