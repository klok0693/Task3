package com.company.company.model.data;

import com.company.company.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by klok on 28.5.18.
 */
@Repository
public interface EmployeeRepository extends GenericEntityRepository<Employee> {
}
