package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.model.User;
import com.example.citizen.repository.IncidentRepository;
import com.example.citizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;
    @Autowired
    UserRepository userRepository;

    public IncidentService(IncidentRepository incidentRepository, UserRepository userRepository) {
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    public List <Incident> getIncidents() {
        return incidentRepository.findAll();
    }

    public void save(Incident incident, List<MultipartFile> files) {
        User user = incident.getUser();
        String login = user.getLogin();
        int userId = userRepository.findId(login);
        user.setIdUser(userId);
        incident.setUser(user);
        incidentRepository.save(incident);
        int incidentId = incident.getIdIncident();
        createMaterialIncident(incidentId, files);
    }

    public void createMaterialIncident(int id, List<MultipartFile> files) {
        Path path = Paths.get("./incidents/" + id);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                assert fileName != null;
                Path targetPath = path.resolve(fileName);
                Files.copy(file.getInputStream(), targetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
