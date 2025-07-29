package org.calendar.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(setterPrefix = "with")
@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendar;
}

