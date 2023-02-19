package com.example.demo.service;

import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Department;
import com.example.demo.entity.Role;
import com.example.demo.form.AccountRequestFormForCreate;
import com.example.demo.form.AccountRequestFormForUpdate;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.specification.AccountSpecification;
import com.example.demo.specification.AccountSpecificationBuilder;
import com.example.demo.specification.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService, UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAccountRepository repository;

    public Page<Account> getAllAccounts(String search, Pageable pageable, Role role) {
        AccountSpecificationBuilder specification = new AccountSpecificationBuilder(search, role);

        return repository.findAll(specification.build(), pageable);
    }

    public Page<Account> getAllAccountsV2(String search, Pageable pageable) {
        SearchCriteria getRoleCriteria = null;
        SearchCriteria searchUsername = new SearchCriteria("username", "Like", search);
        SearchCriteria searchFirstName = new SearchCriteria("firstName", "Like", search);
        SearchCriteria searchLastName = new SearchCriteria("lastName", "Like", search);
        if (search.equals("USER")) {
            getRoleCriteria = new SearchCriteria("role", "Equals", Role.USER);
        }
        if (search.equals("MANAGER")) {
            getRoleCriteria = new SearchCriteria("role", "Equals", Role.MANAGER);
        }
        if (search.equals("ADMIN")) {
            getRoleCriteria = new SearchCriteria("role", "Equals", Role.ADMIN);
        }
//		SearchCriteria getDepartmentCriteria = new SearchCriteria("departmentName", "Equals", filter.getDepartmentName());

        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = new AccountSpecification(searchUsername);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.or(new AccountSpecification(searchFirstName));
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.or(new AccountSpecification(searchLastName));
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.or(new AccountSpecification(getRoleCriteria));
        }

        return repository.findAll(where, pageable);
    }

    public void updateAccountPartially(int id, Map<String, Object> fields) {
        Optional<Account> account = repository.findById(id);

        if (account.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Account.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, account.get(), value);
            });
        }
        repository.save(account.get());
    }

    public void updateAccount(int id, AccountRequestFormForUpdate form) {
        Optional<Account> account = repository.findById(id);

        if (account.isPresent()) {
            account.get().setUsername(form.getUsername());
            account.get().setFirstName(form.getFirstName());
            account.get().setLastName(form.getLastName());
            account.get().setRole(form.getRole());
            Department department = new Department();
            department.setId(form.getDepartmentId());
            account.get().setDepartment(department);
        }
        repository.save(account.get());
    }

    public void deleteAccounts(List<Integer> ids) {
        repository.deleteByIdIn(ids);
    }

    @Override
    public AccountResponseDTO getAccountByID(int id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
            accountResponseDTO.setId(id);
            accountResponseDTO.setUsername(account.get().getUsername());
            accountResponseDTO.setDepartmentName(account.get().getDepartment().getName());
            accountResponseDTO.setRole(account.get().getRole());
            return accountResponseDTO;
        }
        return null;
    }

    @Override
    public void createAccount(AccountRequestFormForCreate form) {
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFirstName(form.getFirstName());
        account.setLastName(form.getLastName());
        account.setRole(form.getRole());

        Department department = new Department();
        department.setId(form.getDepartmentId());
        account.setDepartment(department);

        repository.save(account);
    }

    @Override
    public void deleteById(int id) throws AccountNotFoundException {
        if (isAccountExitById(id)) {
            repository.deleteById(id);

        } else {
            throw new AccountNotFoundException("account not exist");
        }
    }

    @Override
    public boolean isAccountExitById(int id) {
        Optional<Account> account = repository.findById(id);
        return account.isPresent();
    }

    @Override
    public Account getAccountByUsername(String username) throws AccountNotFoundException {
        Account account = repository.findByUsername(username);
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        if(account != null){
            return new User(account.getUsername(),
                            account.getPassword(),
                    AuthorityUtils.createAuthorityList(account.getRole().toString()));
        } else {
            try {
                throw new AccountNotFoundException("Account username not exits");
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

