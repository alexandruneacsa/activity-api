package org.calendar.api.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.calendar.api.aop.measure.ElapsedTime;
import org.calendar.api.exception.CalendarException;
import org.calendar.api.model.dto.CalendarDTO;
import org.calendar.api.populator.CalendarMapper;
import org.calendar.api.repository.CalendarRepository;
import org.calendar.api.service.iface.CalendarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CalendarServiceImplementation implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;

    @Override
    @ElapsedTime
    public List<CalendarDTO> getAllDaysFromCalendar() {

        var calendar = calendarRepository.findAll();

        return calendarMapper.toDTOList(calendar);
    }

    @Override
    @ElapsedTime
    public List<CalendarDTO> getDaysByYear(final Integer year) {

        var calendar = calendarRepository.findAllByYear(year);

        return calendarMapper.toDTOList(calendar);
    }

    @Override
    public CalendarDTO addDay(final CalendarDTO calendarDTO) {

        if (calendarDTO == null) {
            throw new CalendarException("Cannot add a null calendar record.");
        }

        var entity = calendarMapper.toEntity(calendarDTO);
        var savedEntity = calendarRepository.save(entity);

        return calendarMapper.toDTO(savedEntity);
    }

    @Override
    public CalendarDTO updateDay(final CalendarDTO calendarDTO) {

        if (calendarDTO == null || calendarDTO.getId() == null) {
            throw new CalendarException("Record to update is null or has no id.");
        }

        var exists = calendarRepository.existsById(calendarDTO.getId());
        if (!exists) {
            throw new CalendarException("No record found to update with id: " + calendarDTO.getId());
        }

        var updatedEntity = calendarRepository.save(calendarMapper.toEntity(calendarDTO));

        return calendarMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteDay(final Long id) {

        calendarRepository.deleteById(id);
    }

    @Override
    public void deleteAllDays() {

        calendarRepository.deleteAll();
    }
}
