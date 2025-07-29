package org.calendar.api.repository;

import org.calendar.api.model.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
	
	List<CalendarEntity> findAllByYear(Integer year);
}
