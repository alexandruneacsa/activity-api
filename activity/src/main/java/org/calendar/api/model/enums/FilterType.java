package org.calendar.api.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FilterType {
	
	EQUALS("equals"), NOT_EQUALS("not-equals"), LIKE("like"), NOT_LIKE("not-like");
	
	private final String value;
	
	FilterType(String value) {
		
		this.value = value;
	}
	
	public static FilterType get(String value) {
		
		return Arrays.stream(FilterType.values())
			.filter(item -> item.value.equals(value))
			.findFirst().orElse(EQUALS);
	}
}
