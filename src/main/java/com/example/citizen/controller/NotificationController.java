package com.example.citizen.controller;

import com.example.citizen.data.NotificationData;
import com.example.citizen.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    NotificationService notificationService;
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody NotificationData notificationData) {
        int notificationId = notificationData.getNotificationId();
        int userId = notificationData.getUserId();
        notificationService.addNotification(userId, notificationId);
        return HttpStatus.OK.value();
    }
}
