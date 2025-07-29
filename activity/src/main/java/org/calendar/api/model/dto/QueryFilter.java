package org.calendar.api.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryFilter {
	
	@NotNull
	private String key;
	@NotNull
	private String condition;
	@NotNull
	private Object value;
}
