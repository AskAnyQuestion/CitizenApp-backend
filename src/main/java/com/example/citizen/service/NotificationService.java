package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import com.example.citizen.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(Incident incident, User user) {
        Notification notification = new Notification(incident, user);
        notificationRepository.save(notification);
    }
}
