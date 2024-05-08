package com.example.citizen.controller;

import com.example.citizen.data.UserData;
import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import com.example.citizen.service.UserService;
import com.example.citizen.data.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/authorization")
    @ResponseBody
    public int authorization(@RequestBody LoginData loginData) {
        if (userService.findUser(loginData))
            return HttpStatus.OK.value();
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @PostMapping("/registration")
    @ResponseBody
    public int registration(@RequestBody User user) {
        userService.save(user);
        return HttpStatus.OK.value();
    }

    @PostMapping("/update")
    @ResponseBody
    public int update(@RequestBody User user) {
        userService.update(user);
        return HttpStatus.OK.value();
    }

    @PostMapping("/get")
    @ResponseBody
    public List<Notification> get(@RequestBody UserData userData) {
        User user = userService.getUser(userData);
        return user.getNotifications();
    }
}
