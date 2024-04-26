package com.example.citizen.controller;

import com.example.citizen.model.User;
import com.example.citizen.service.UserService;
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
    public ResponseEntity<HttpStatus> authorization(Long phone, String password) {
        boolean findUser = userService.findUser(phone, password);
        if (findUser)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<HttpStatus> registration(User user) {
        if (user != null) {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
