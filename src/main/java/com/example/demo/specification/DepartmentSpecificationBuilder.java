package com.example.demo.specification;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecificationBuilder {
    private DepartmentFilterForm filter;
    private String search;

    public DepartmentSpecificationBuilder(DepartmentFilterForm filter, String search) {
        this.filter = filter;
        this.search = search;
    }

    @SuppressWarnings("deprecation")
    public Specification<Department> build() {

        SearchCriteria searchName = new SearchCriteria("name", "Like", search);

        SearchCriteria minCreatedDateCriteria = new SearchCriteria("createdDate", ">=", filter.getMin_created_date());
        SearchCriteria maxCreatedDateCriteria = new SearchCriteria("createdDate", "<=", filter.getMax_created_date());

        Specification<Department> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = new DepartmentSpecification(searchName);
        }

        // filer by create Date
        if (filter.getMin_created_date() != null) {
            if (where != null) {
                where = where.and(new DepartmentSpecification(minCreatedDateCriteria));
            } else {
                where = new DepartmentSpecification(minCreatedDateCriteria);
            }
        }

        if (filter.getMax_created_date() != null) {
            if (where != null) {
                where = where.and(new DepartmentSpecification(maxCreatedDateCriteria));
            } else {
                where = new DepartmentSpecification(maxCreatedDateCriteria);
            }
        }

        return where;
    }

}
