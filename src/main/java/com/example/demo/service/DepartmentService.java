package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentFilterForm;
import com.example.demo.form.DepartmentRequestFormForCreate;
import com.example.demo.form.DepartmentRequestFormForUpdate;
import com.example.demo.repository.IDepartmentRepository;
import com.example.demo.specification.DepartmentSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository repository;

    public Page<Department> getAllDepartments(String search, Pageable pageable, DepartmentFilterForm filter) {
        DepartmentSpecificationBuilder specification = new DepartmentSpecificationBuilder(filter, search);

        return repository.findAll(specification.build(), pageable);
    }

    @Override
    public void createDepartment(DepartmentRequestFormForCreate form) {
        repository.save(form.toEntity());
    }

    @Override
    public void addAccountToDepartment(Account account, Department department) {

    }

    @Override
    public void updateDepartment(int id, DepartmentRequestFormForUpdate form) {
        Optional<Department> department = repository.findById(id);
        if (department.isPresent()) {
            department.get().setName(form.getName());
            department.get().setType(form.getType());
        }
        repository.save(department.get());
    }

    @Override
    public void deleteDepartment(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public Department getDepartmentByID(int id) {
        return repository.findById(id).get();
    }

}
