package com.example.demo.form;

import com.example.demo.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentRequestFormForUpdate {
    private String name;

    private Department.Type type;

}
