package com.example.demo.entity;

import com.example.demo.converter.DepartmentTypeConvert;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DepartmentName", length = 50)
    @NonNull
    private String name;

    @Column(name = "TotalMember")
    private int totalMember;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "CreateDate")
    private Date createdDate;

    @Column(name = "Type", nullable = false)
    @Convert(converter = DepartmentTypeConvert.class)
    private Type type;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Department(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        DEV("Dev"), TEST("Test"), ScrumMaster("ScrumMaster"), PM("PM");

        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type toEnum(String sqlValue) {
            for (Type type : Type.values()) {
                if (type.getValue().equals(sqlValue)) {
                    return type;
                }
            }
            return null;
        }

    }

}

