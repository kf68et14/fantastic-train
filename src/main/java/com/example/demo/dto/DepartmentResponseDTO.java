package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentResponseDTO {
    private int id;

    private String name;

    private Department.Type type;

//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date createdDate;

    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDTO {

        @JsonProperty("accountId")
        private int id;

        private String username;
    }
}

