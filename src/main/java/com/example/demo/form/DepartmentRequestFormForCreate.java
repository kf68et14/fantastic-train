package com.example.demo.form;

import com.example.demo.entity.Department;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DepartmentRequestFormForCreate {
    @NotNull(message = "name needed for create department")
    private String name;

    private Department.Type type;

}
