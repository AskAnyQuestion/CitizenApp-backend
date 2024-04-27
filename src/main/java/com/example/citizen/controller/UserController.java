package com.example.citizen.controller;

import com.example.citizen.model.User;
import com.example.citizen.service.UserService;
import com.example.citizen.utils.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        boolean findUser = userService.findUser(loginData);
        if (findUser)
            return HttpStatus.OK.value();
        else
            return HttpStatus.FORBIDDEN.value();

    }

    @PostMapping("/registration")
    @ResponseBody
    public int registration(@RequestBody User user) {
        if (user != null) {
            userService.save(user);
            return HttpStatus.OK.value();
        } else
            return HttpStatus.FORBIDDEN.value();
    }
}
