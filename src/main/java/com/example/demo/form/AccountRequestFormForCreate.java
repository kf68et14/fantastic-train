package com.example.demo.form;

import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestFormForCreate {
    @NotBlank(message = "username must not be null")
    private String username;

    @NotBlank(message = "firstname must not be null")
    private String firstName;

    @NotBlank(message = "lastname must not be null")
    private String lastName;

    // @Pattern(regexp = "USER|MANAGER|ADMIN", message = "the role must be USER, MANAGER or ADMIN")
    private Role role;

    private int departmentId;

}
