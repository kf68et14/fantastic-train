package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Data
@NoArgsConstructor
public class AccountResponseDTO {

    private int id;

    private String username;

    private String departmentName;

//    @Formula(" concat(LastName, ' ', FirstName)")
//    private String fullName;

    private Role role;
}
