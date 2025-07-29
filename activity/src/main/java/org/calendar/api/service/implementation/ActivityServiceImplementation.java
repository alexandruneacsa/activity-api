package org.calendar.api.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.calendar.api.model.dto.ActivityDTO;
import org.calendar.api.model.projection.ActivityProjection;
import org.calendar.api.populator.ActivityMapper;
import org.calendar.api.repository.ActivityRepository;
import org.calendar.api.service.iface.ActivityService;
import org.calendar.api.exception.ActivityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ActivityServiceImplementation implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityDTO> getAllActivities() {

        var activities = activityRepository.findAll();

        return activityMapper.toDTOList(activities);
    }

    @Override
    public Optional<ActivityProjection> findByName(String name) {

        return activityRepository.findByName(name);
    }

    @Override
    public Optional<ActivityProjection> findActivityNameById(Long id) {

        return activityRepository.findActivityNameById(id);
    }

    @Override
    public Page<ActivityProjection> findAllByOrderByNameAsc(Pageable pageable) {

        return activityRepository.findAllByOrderByNameAsc(pageable);
    }

    @Override
    public ActivityDTO addActivity(ActivityDTO activityDTO) {

        if (activityDTO == null) {
            throw new ActivityException("Cannot add a null activity record.");
        }

        var entity = activityMapper.toEntity(activityDTO);
        var savedEntity = activityRepository.save(entity);

        return activityMapper.toDTO(savedEntity);
    }

    @Override
    public ActivityDTO updateActivity(ActivityDTO activityDTO) {

        if (activityDTO == null || activityDTO.getId() == null) {
            throw new ActivityException("Record to update is null or has no id.");
        }

        var exists = activityRepository.existsById(activityDTO.getId());
        if (!exists) {
            throw new ActivityException("No record found to update with id: " + activityDTO.getId());
        }

        var updatedEntity = activityRepository.save(activityMapper.toEntity(activityDTO));

        return activityMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteActivity(Long id) {

        activityRepository.deleteById(id);
    }

    @Override
    public void deleteAllActivities() {

        activityRepository.deleteAll();
    }
}
