package com.example.crm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthControllerDTO {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String location;
    private String role;
}
