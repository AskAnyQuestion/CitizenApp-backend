package com.example.citizen.controller;

import com.example.citizen.data.UpdateData;
import com.example.citizen.data.UserData;
import com.example.citizen.model.Incident;
import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import com.example.citizen.service.UserService;
import com.example.citizen.data.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        userService.save(user, false);
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
    public List<Incident> get(@RequestBody UserData userData) {
        User user = userService.getUser(userData);
        List<Incident> incidentList = new ArrayList<>();
        for (Notification notification : user.getNotifications()) {
            Incident incident = notification.getIncident();
            incidentList.add(incident);
        }
        return incidentList;
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody UserData userData) {
        User user = userService.getUser(userData);
        if (user != null)
            userService.delete(user);
        return HttpStatus.OK.value();
    }

    @PostMapping("/updates")
    @ResponseBody
    public int updates(@RequestBody UpdateData updateData) {
        User user = userService.getUser(new UserData(updateData.getOldLogin(), updateData.getOldPhone()));
        user.setLogin(updateData.getNewLogin());
        user.setPhone(updateData.getNewPhone());
        userService.save(user, true);
        return HttpStatus.OK.value();
    }
}
