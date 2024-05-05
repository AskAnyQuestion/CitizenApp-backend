package com.example.citizen.controller;

import com.example.citizen.model.Incident;
import com.example.citizen.model.User;
import com.example.citizen.service.FirebaseMessagingService;
import com.example.citizen.service.IncidentService;
import com.example.citizen.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    IncidentService incidentService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/add")
    @ResponseBody
    @Deprecated
    public int add(@RequestPart("incident") Incident incident,
                   @RequestPart("files") List<MultipartFile> files) throws FirebaseMessagingException {
        User user = incidentService.save(incident, files);
        notificationService.save(incident, user);
        firebaseMessagingService.sendNotification(user.getIdUser(), incident);
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
