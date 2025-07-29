package org.calendar.api.unit;

import org.calendar.api.model.dto.ActivityDTO;
import org.calendar.api.model.entity.ActivityEntity;
import org.calendar.api.populator.ActivityMapper;
import org.calendar.api.repository.ActivityRepository;
import org.calendar.api.service.implementation.ActivityServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityMapper activityMapper;

    @InjectMocks
    private ActivityServiceImplementation activityServiceImplementation;

    @Test
    @DisplayName("Test get all activities.")
    void givenActivitiesExist_whenGetAllActivities_thenReturnMappedDTOList() {

        // Given
        var mockEntities = List.of(new ActivityEntity(), new ActivityEntity());
        var mockDTOs = List.of(new ActivityDTO(), new ActivityDTO());

        when(activityRepository.findAll()).thenReturn(mockEntities);
        when(activityMapper.toDTOList(mockEntities)).thenReturn(mockDTOs);

        // When
        List<ActivityDTO> result = activityServiceImplementation.getAllActivities();

        // Then
        assertEquals(mockDTOs.size(), result.size());
        verify(activityRepository).findAll();
        verify(activityMapper).toDTOList(mockEntities);
    }
}


