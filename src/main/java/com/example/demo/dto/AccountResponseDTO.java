package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AccountResponseDTO {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String departmentName;

    private int departmentId;

//    @Formula(" concat(LastName, ' ', FirstName)")
//    private String fullName;

    private Role role;
}
