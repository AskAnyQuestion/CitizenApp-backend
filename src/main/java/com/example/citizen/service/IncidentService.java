package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.model.User;
import com.example.citizen.repository.IncidentRepository;
import com.example.citizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public List<Incident> getIncidents() {
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
        Path path = Paths.get("./incidents/id/" + id);
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

    public Resource getMaterials() throws Exception {
        Path zipFile = Paths.get("./incidents/resources.zip");
        Path sourcePath = Paths.get("./incidents/id/");
        File source = new File(sourcePath.toUri());
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(zipFile.toFile()));
        addFilesToZip("", source, stream);
        stream.close();
        InputStream inputStream = new FileInputStream(zipFile.toFile());
        return new InputStreamResource(inputStream);
    }

    public Resource getMaterial(String id) throws Exception {
        Path zipFile = Paths.get("./incidents/id/" + id + "/" + id + ".zip");
        Path sourcePath = Paths.get("./incidents/id/" + id);
        File source = new File(sourcePath.toUri());
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(zipFile.toFile()));
        addFilesToZip("", source, stream);
        stream.close();
        InputStream inputStream = new FileInputStream(zipFile.toFile());
        return new InputStreamResource(inputStream);
    }

    private void addFilesToZip(String prefix, File folder, ZipOutputStream zos) throws IOException {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                addFilesToZip(prefix + file.getName() + "/", file, zos);
            } else {
                zos.putNextEntry(new ZipEntry(prefix + file.getName()));
                zos.write(Files.readAllBytes(file.toPath()));
                zos.closeEntry();
            }
        }
    }
}
