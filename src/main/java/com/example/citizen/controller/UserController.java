package com.example.citizen.controller;

import com.example.citizen.model.User;
import com.example.citizen.service.UserService;
import com.example.citizen.data.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authorization")
    @ResponseBody
    public int authorization(@RequestBody LoginData loginData) {
        if (userService.findUser(loginData))
            return HttpStatus.OK.value();
        else
            return HttpStatus.INTERNAL_SERVER_ERROR.value();

    }

    @PostMapping("/registration")
    @ResponseBody
    public int registration(@RequestBody User user) {
        if (user != null) {
            userService.save(user);
            return HttpStatus.OK.value();
        } else
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
