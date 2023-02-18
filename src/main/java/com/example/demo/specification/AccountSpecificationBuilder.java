package com.example.demo.specification;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountSpecificationBuilder {
    private Role role;

    private String search;

    public AccountSpecificationBuilder(String search, Role role) {
        this.search = search;
        this.role = role;
    }

    @SuppressWarnings("deprecation")
    public Specification<Account> build() {
        SearchCriteria searchName = new SearchCriteria("username", "Like", search);
        SearchCriteria searchFirstName = new SearchCriteria("firstName", "Like", search);
        SearchCriteria searchLastName = new SearchCriteria("lastName", "Like", search);

        SearchCriteria getRoleCriteria = new SearchCriteria("role", "Equals", role);
        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = new AccountSpecification(searchName);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.or(new AccountSpecification(searchFirstName));
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.or(new AccountSpecification(searchLastName));
        }

        if (role != null) {
            if (where != null) {
                where = where.and(new AccountSpecification(getRoleCriteria));
            } else {
                where = new AccountSpecification(getRoleCriteria);
            }
        }
        return where;
    }


}
