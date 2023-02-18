package com.example.demo.form;

import com.example.demo.entity.Department;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class DepartmentRequestFormForCreate {
    @NotBlank(message = "name must not be blank")
    private String name;

    @Pattern(regexp = "DEV|TEST|ScrumMaster|PM", message = "the type must be Dev, Test, ScumMaster or PM")
    private Department.Type type;

    public Department toEntity() {
        return new Department(name, type);
    }
}
