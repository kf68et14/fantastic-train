package com.example.demo.service;

import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.form.AccountRequestFormForCreate;
import com.example.demo.form.AccountRequestFormForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

public interface IAccountService {

    Page<Account> getAllAccounts(String search, Pageable page, Role role);

    AccountResponseDTO getAccountByID(int id) throws AccountNotFoundException;

    void updateAccount(int id, AccountRequestFormForUpdate form);

    void updateAccountPartially(int id, Map<String, Object> fields) throws AccountNotFoundException;

    void deleteAccounts(List<Integer> ids) throws AccountNotFoundException;

    void createAccount(AccountRequestFormForCreate form);

    void deleteById(int id) throws AccountNotFoundException;

    boolean isAccountExitById(int id);

    Account getAccountByUsername(String username) throws AccountNotFoundException;
}
