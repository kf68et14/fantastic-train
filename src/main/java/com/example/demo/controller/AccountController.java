package com.example.demo.controller;

import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.form.AccountRequestFormForCreate;
import com.example.demo.form.AccountRequestFormForUpdate;
import com.example.demo.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountService service;

    @GetMapping
    public ResponseEntity<?> getAllAccounts(@RequestParam(value = "search", required = false) String search,
                                            Pageable pageable,
                                            @RequestParam(value = "role", required = false) Role role) {


        Page<Account> accounts = service.getAllAccounts(search, pageable, role);
        Page<AccountResponseDTO> result = accounts.map(account -> modelMapper.map(account, AccountResponseDTO.class));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") int id) throws AccountNotFoundException {
        return new ResponseEntity<>(service.getAccountByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequestFormForCreate form) {
        service.createAccount(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update by id
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id") int id,
                                           @RequestBody @Valid AccountRequestFormForUpdate form) {
        service.updateAccount(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update partial information by id
    @PatchMapping(value = "/update/v2/{id}")
    public ResponseEntity<?> updateAccountPartially(@PathVariable(name = "id") int id, @RequestBody Map<String, Object> fields) throws AccountNotFoundException {
        service.updateAccountPartially(id, fields);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) throws AccountNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete nhieu account
    @DeleteMapping
    public ResponseEntity<?> deleteAccounts(@RequestBody List<Integer> ids) throws AccountNotFoundException {
        service.deleteAccounts(ids);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
