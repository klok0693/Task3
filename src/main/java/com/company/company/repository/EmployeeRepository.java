package com.company.company.repository;

import com.company.company.model.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by klok on 28.5.18.
 */
@Repository
public interface EmployeeRepository extends GenericEntityRepository<Employee> {
}
