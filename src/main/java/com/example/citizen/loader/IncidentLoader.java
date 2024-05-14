package com.example.citizen.loader;

import com.example.citizen.model.Incident;
import com.example.citizen.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class IncidentLoader implements CommandLineRunner {
    @Autowired
    private IncidentService incidentService;
    public IncidentLoader(IncidentService incidentService) {
        this.incidentService = incidentService;
    }
    @Override
    public void run(String... args) {
        Incident incident = new Incident();
        incident.setIdIncident(1);
        incident.setEventTime(new Timestamp(new Date().getTime()));
        incident.setLatitude(57.219547);
        incident.setLongitude(41.907002);
        incident.setEventDescription("Бездомные нападают на прохожих");
        incident.setUser(null);
        incidentService.save(incident);

        Incident incidentTwo = new Incident();
        incidentTwo.setIdIncident(2);
        incidentTwo.setEventTime(new Timestamp(new Date().getTime()));
        incidentTwo.setLatitude(57.218721);
        incidentTwo.setLongitude(41.915064);
        incidentTwo.setEventDescription("Массовая драка возле кафе");
        incidentTwo.setUser(null);
        incidentService.save(incidentTwo);

        Incident incidentThree = new Incident();
        incidentThree.setIdIncident(3);
        incidentThree.setEventTime(new Timestamp(new Date().getTime()));
        incidentThree.setLatitude(57.218915);
        incidentThree.setLongitude(41.909234);
        incidentThree.setEventDescription("Мигранты угрожают прохожим");
        incidentThree.setUser(null);
        incidentService.save(incidentThree);
    }
}
