package com.example.citizen.controller;

import com.example.citizen.data.IncidentData;
import com.example.citizen.model.Incident;
import com.example.citizen.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @PostMapping("/add")
    @ResponseBody
    public int add(/*@RequestBody Incident incident, */@RequestPart("files") List<MultipartFile> files) {
        return HttpStatus.OK.value();
    }
}
