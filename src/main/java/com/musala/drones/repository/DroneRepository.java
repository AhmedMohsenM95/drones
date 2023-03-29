package com.musala.drones.repository;

import com.musala.drones.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    Drone findBySerialNumber(String serialNumber);
    List<Drone> findAllByBatteryCapacityIsGreaterThan(int batteryCapacity);
}
