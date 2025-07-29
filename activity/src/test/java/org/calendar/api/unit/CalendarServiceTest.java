package org.calendar.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.calendar.api.model.dto.CalendarDTO;
import org.calendar.api.model.entity.CalendarEntity;
import org.calendar.api.populator.CalendarMapper;
import org.calendar.api.repository.CalendarRepository;
import org.calendar.api.service.implementation.CalendarServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTest {
	
	@Mock
	private CalendarRepository calendarRepository;
	
	@Mock
	private CalendarMapper calendarMapper;
	
	@InjectMocks
	private CalendarServiceImplementation calendarServiceImplementation;
	
	@Test
	@DisplayName("Test get all days from calendar.")
	void givenCalendarExist_whenGetAllDays_thenReturnMappedDTOList() {
		
		// Given
		var mockEntities = List.of(new CalendarEntity(), new CalendarEntity());
		var mockDTOs = List.of(new CalendarDTO(), new CalendarDTO());
		
		when(calendarRepository.findAll()).thenReturn(mockEntities);
		when(calendarMapper.toDTOList(mockEntities)).thenReturn(mockDTOs);
		
		// When
		var result = calendarServiceImplementation.getAllDaysFromCalendar();
		
		// Then
		assertEquals(mockDTOs.size(), result.size());
		verify(calendarRepository).findAll();
		verify(calendarMapper).toDTOList(mockEntities);
	}
}
