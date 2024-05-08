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

    public Notification save(Incident incident, User user) {
        Notification notification = new Notification(incident, user);
        return notificationRepository.save(notification);
    }

    public void addNotification(int userId, int notificationId) {
        User user = userRepository.getUserById(userId);
        Notification notification =  notificationRepository.getNotificationById(notificationId);
        List<Notification> list = user.getNotifications();
        list.add(notification);
        user.setNotifications(list);
        userRepository.save(user);
    }
}
