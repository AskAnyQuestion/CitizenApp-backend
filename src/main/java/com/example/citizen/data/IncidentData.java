package com.example.citizen.data;

import com.example.citizen.model.Incident;
import org.springframework.core.io.FileSystemResource;

public class IncidentData {
    private Incident incident;
    private FileSystemResource resource;

    public IncidentData() {}

    public IncidentData(Incident incident, FileSystemResource resource) {
        this.incident = incident;
        this.resource = resource;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public void setResource(FileSystemResource resource) {
        this.resource = resource;
    }

    public Incident getIncident() {
        return incident;
    }

    public FileSystemResource getResource() {
        return resource;
    }
}
