package com.example.citizen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationData {
    private int userId;
    private int notificationId;
}
