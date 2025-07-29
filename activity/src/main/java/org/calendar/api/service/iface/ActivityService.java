package org.calendar.api.service.iface;

import org.calendar.api.model.dto.ActivityDTO;
import org.calendar.api.model.projection.ActivityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    List<ActivityDTO> getAllActivities();

    Optional<ActivityProjection> findByName(final String name);

    Optional<ActivityProjection> findActivityNameById(final Long id);

    Page<ActivityProjection> findAllByOrderByNameAsc(final Pageable pageable);

    ActivityDTO addActivity(final ActivityDTO activityDTO);

    ActivityDTO updateActivity(final ActivityDTO activityDTO);

    void deleteActivity(final Long id);

    void deleteAllActivities();
}
