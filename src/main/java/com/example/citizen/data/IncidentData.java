package com.example.citizen.data;

import com.example.citizen.model.Incident;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public class IncidentData {
    private Incident incident;
    private ArrayList<MultipartFile> list;

    public IncidentData() {}

    public IncidentData(Incident incident, ArrayList <MultipartFile> list) {
        this.incident = incident;
        this.list = list;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public void setList(ArrayList<MultipartFile> list) {
        this.list = list;
    }

    public Incident getIncident() {
        return incident;
    }

    public ArrayList<MultipartFile> getList() {
        return list;
    }
}
