package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import com.example.citizen.repository.NotificationRepository;
import com.example.citizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    public NotificationService(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void save(Incident incident, User user) {
        Notification notification = new Notification(incident, user);
        notificationRepository.save(notification);

    }
}
