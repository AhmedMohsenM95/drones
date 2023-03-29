package com.musala.drones.model.entity;


import com.musala.drones.model.enums.DroneModel;
import com.musala.drones.model.enums.DroneState;
import lombok.*;

import javax.persistence.*;

@Table(name = "MEDICATION")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    //Weight limit is in gr
    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "CODE")
    private String code;

    @Lob
    @Column(name = "IMAGE_DATA", columnDefinition = "mediumblob")
    private byte[] image;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @Column(name = "IMAGE_EXTENSION")
    private String imageExtension;

    @ManyToOne
    @JoinColumn(name = "DRONE_SERIAL_NUMBER", referencedColumnName = "SERIAL_NUMBER")
    private Drone drone;

}
