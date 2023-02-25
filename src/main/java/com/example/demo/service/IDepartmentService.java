package com.example.demo.service;

import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentFilterForm;
import com.example.demo.form.DepartmentRequestFormForCreate;
import com.example.demo.form.DepartmentRequestFormForUpdate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IDepartmentService {

    public DepartmentResponseDTO getDepartmentByID(int id) throws Exception;

    Page<Department> getAllDepartments(String search, Pageable pageable, DepartmentFilterForm filterForm);

    void createDepartment(DepartmentRequestFormForCreate form);

    void updateDepartment(int id, DepartmentRequestFormForUpdate form);

    void deleteDepartment(int id);
}
