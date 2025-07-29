package org.calendar.api.service.iface;

import org.calendar.api.model.dto.CalendarDTO;

import java.util.List;

public interface CalendarService {

    List<CalendarDTO> getAllDaysFromCalendar();

    List<CalendarDTO> getDaysByYear(final Integer year);

    CalendarDTO addDay(CalendarDTO calendarDTO);

    CalendarDTO updateDay(final CalendarDTO calendarDTO);

    void deleteDay(Long id);

    void deleteAllDays();
}
