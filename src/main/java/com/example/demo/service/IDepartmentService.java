package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentFilterForm;
import com.example.demo.form.DepartmentRequestFormForCreate;
import com.example.demo.form.DepartmentRequestFormForUpdate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IDepartmentService {

    public Department getDepartmentByID(int id);

    Page<Department> getAllDepartments(String search, Pageable pageable, DepartmentFilterForm filterForm);

    void createDepartment(DepartmentRequestFormForCreate form);

    void addAccountToDepartment(Account account, Department department);

    void updateDepartment(int id, DepartmentRequestFormForUpdate form);

    void deleteDepartment(int id);
}
