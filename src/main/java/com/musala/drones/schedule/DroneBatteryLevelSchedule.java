package com.musala.drones.schedule;

import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.entity.DroneBatteryAudit;
import com.musala.drones.repository.DroneBatteryAuditRepository;
import com.musala.drones.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class DroneBatteryLevelSchedule {

    private final DroneRepository droneRepository;
    private final DroneBatteryAuditRepository droneBatteryAuditRepository;

    //60,000 = 1 minute
    //used to monitor drones battery levels every minute and save audit to database
    @Scheduled(fixedRate = 60000)
    public void droneBatteryLevelScheduleFixedRate() {
        log.info("droneBatteryLevelScheduleFixedRate started...");
        List<Drone> droneList = droneRepository.findAll();
        List<DroneBatteryAudit> droneBatteryAuditList = new ArrayList<>();
        for (Drone drone : droneList) {
            droneBatteryAuditList.add(DroneBatteryAudit.builder()
                            .drone(drone)
                            .batteryLevel(drone.getBatteryCapacity())
                            .createdAt(LocalDateTime.now())
                    .build());
        }
        droneBatteryAuditRepository.saveAll(droneBatteryAuditList);
        log.info("droneBatteryLevelScheduleFixedRate ended...");
    }
}
