package org.calendar.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Entity
@Table(name = "calendar")
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short year;

    private Short month;

    private Short day;
}
