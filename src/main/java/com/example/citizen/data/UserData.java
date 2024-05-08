package com.example.citizen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {
    private String login;
    private Long phone;

}
