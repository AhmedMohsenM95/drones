package com.musala.drones.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "DRONE_BATTERY_AUDIT")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneBatteryAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "DRONE_SERIAL_NUMBER", referencedColumnName = "SERIAL_NUMBER")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Drone drone;

    @Column(name = "BATTERY_LEVEL")
    private int batteryLevel;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}
