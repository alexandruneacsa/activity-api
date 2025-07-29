package org.calendar.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private Long id;

    private String name;

    private String description;

    private Long calendarId;
}

