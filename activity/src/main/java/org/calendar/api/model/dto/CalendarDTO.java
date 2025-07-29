package org.calendar.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {

	private Long    id;

	private Integer year;

	private Integer month;

	private Integer day;
}
