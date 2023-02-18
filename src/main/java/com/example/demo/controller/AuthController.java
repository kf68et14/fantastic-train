package com.example.demo.controller;

import com.example.demo.dto.LoginInfoDTO;
import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountService service;

    @GetMapping("/login")
    public LoginInfoDTO login (Principal principal) throws AccountNotFoundException {
        String username = principal.getName();
        Account entity = service.getAccountByUsername(username);

        LoginInfoDTO dto = modelMapper.map(entity, LoginInfoDTO.class);
        return dto;
    }
}
