package com.example.citizen.service;

import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import com.example.citizen.repository.NotificationRepository;
import com.example.citizen.repository.UserRepository;
import com.example.citizen.data.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    BCryptPasswordEncoder bCryptEncoder;
    UserRepository userRepository;
    NotificationRepository notificationRepository;
    @Autowired
    public UserService(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.bCryptEncoder = new BCryptPasswordEncoder(12);
    }

    public boolean findUser(LoginData loginData) {
        Long phone = loginData.getPhone();
        String password = loginData.getPassword();
        User user = userRepository.findUserByPhone(phone);
        if (user != null) {
            String bdPass = user.getPassword();
            return bCryptEncoder.matches(password, bdPass);
        }
        return false;
    }
    public void save (User user) {
         String hash = bCryptEncoder.encode(user.getPassword());
         user.setPassword(hash);
         userRepository.save(user);
    }
    public void update (User user) {
        User bdUser = userRepository.findUser(user.getLogin(), user.getPhone());
        bdUser.setToken(user.getToken());
        userRepository.updateUserByToken(bdUser.getIdUser(), bdUser.getToken());
    }

    public void addNotification(int userId, int notificationId) {
        User user = userRepository.getUserById(userId);
        Notification notification =  notificationRepository.getNotificationById(notificationId);
        List<Notification> list = user.getNotifications();
        list.add(notification);
        user.setNotifications(list);
        User u = userRepository.save(user);
        System.out.println(u);
    }
}
