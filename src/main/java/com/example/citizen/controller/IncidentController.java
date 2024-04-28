package com.example.citizen.controller;

import com.example.citizen.data.IncidentData;
import com.example.citizen.model.Incident;
import com.example.citizen.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestPart ("incident") Incident incident,
                   @RequestPart ("files") List<MultipartFile> files) {
        incidentService.save(incident, files);
        return HttpStatus.OK.value();
    }

    @GetMapping("/get")
    @ResponseBody
    public List <Object> get() { // TODO
        File file = new File("file0");
        List <Object> objects = new ArrayList<>();
        objects.add(new FileSystemResource(file));
        objects.add(new Incident());
        return objects;
    }
}
