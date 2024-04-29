package com.example.citizen.controller;

import com.example.citizen.model.Incident;
import com.example.citizen.service.IncidentService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestPart("incident") Incident incident,
                   @RequestPart("files") List<MultipartFile> files) {
        incidentService.save(incident, files);
        return HttpStatus.OK.value();
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Incident> get() {
        return incidentService.getIncidents();
    }

    @GetMapping("/material/{id}")
    @ResponseBody
    public Resource material(@PathVariable String id) throws Exception {
        return incidentService.getMaterial(id);
    }

    @GetMapping("/materials")
    @ResponseBody
    public Resource materials() throws Exception {
        return incidentService.getMaterials();
    }

}
