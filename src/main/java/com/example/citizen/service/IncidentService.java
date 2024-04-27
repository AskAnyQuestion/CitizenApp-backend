package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public void save(Incident incident) {
        incidentRepository.save(incident);
    }
}
