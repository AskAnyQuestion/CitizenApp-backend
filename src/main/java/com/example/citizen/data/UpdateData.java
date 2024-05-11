package com.example.citizen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateData {
    private String oldLogin;
    private Long oldPhone;
    private String newLogin;
    private Long newPhone;
}
