package com.musala.drones.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musala.drones.model.enums.DroneModel;
import com.musala.drones.model.enums.DroneState;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "DRONE")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drone {

    @Id
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "MODEL")
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    //Weight limit is in gr
    @Column(name = "WEIGHT_LIMIT")
    private int weightLimit;

    @Column(name = "BATTERY_CAPACITY")
    private int batteryCapacity;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private DroneState droneState;

    @OneToMany(mappedBy = "drone", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Medication> medications;

}
