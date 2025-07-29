package org.calendar.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.calendar.api.model.dto.ActivityDTO;
import org.calendar.api.model.projection.ActivityProjection;
import org.calendar.api.service.iface.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "Get all activities.")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<ActivityDTO> getAllDays() {

        return activityService.getAllActivities();
    }

    @Operation(summary = "Get activity by name.")
    @GetMapping("get-activity-by-name")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public Optional<ActivityProjection> getActivityByName(@RequestParam final String name) {

        return activityService.findByName(name);
    }

    @Operation(summary = "Get activity name by id.")
    @GetMapping("get-activity-name")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public Optional<ActivityProjection> getActivityName(@RequestParam final Long id) {

        return activityService.findActivityNameById(id);
    }

    @Operation(summary = "Get all activities ordered by name.")
    @GetMapping("get-activities")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public Page<ActivityProjection> getAllActivities(@SortDefault(sort = "name",
            direction = Sort.Direction.ASC) @PageableDefault(size = 25) final Pageable pageable) {

        return activityService.findAllByOrderByNameAsc(pageable);
    }

    @Operation(summary = "Add a activity in calendar.")
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ActivityDTO addActivity(@RequestBody final ActivityDTO activityDTO) {

        return activityService.addActivity(activityDTO);
    }

    @Operation(summary = "Update a activity from calendar.")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ActivityDTO updateActivity(@RequestBody final ActivityDTO activityDTO) {

        return activityService.updateActivity(activityDTO);
    }

    @Operation(summary = "Delete a activity from calendar.")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteActivity(@RequestParam final Long id) {

        activityService.deleteActivity(id);
    }
}
