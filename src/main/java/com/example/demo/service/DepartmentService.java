package com.example.demo.service;

import com.example.demo.dto.DepartmentResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public DepartmentResponseDTO getDepartmentByID(int id) throws Exception {

        Optional<Department> department = repository.findById(id);
        DepartmentResponseDTO result = new DepartmentResponseDTO();

        if (department.isPresent()){
            result.setName(department.get().getName());
            result.setType(department.get().getType());
//            result.setCreatedDate(department.get().getCreatedDate());

            List<Account> accountList = department.get().getAccounts();
            List<DepartmentResponseDTO.AccountDTO> dtoList = new ArrayList<>();
            for (Account account : accountList){
                DepartmentResponseDTO.AccountDTO accountDTO = new DepartmentResponseDTO.AccountDTO();
                accountDTO.setId(account.getId());
                accountDTO.setUsername(account.getUsername());
                dtoList.add(accountDTO);
            }
            result.setAccounts(dtoList);
        } else {
            throw new Exception("Department id not exits");
        }
        return result;
    }

    @Override
    public void createDepartment(DepartmentRequestFormForCreate form) {
        Department department = new Department();
        department.setName(form.toEntity().getName());
        department.setType(form.toEntity().getType());
        repository.save(department);
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



}
