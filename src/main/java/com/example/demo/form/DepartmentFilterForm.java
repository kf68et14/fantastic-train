package com.example.demo.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DepartmentFilterForm {
    private Date min_created_date;

    private Date max_created_date;
}
