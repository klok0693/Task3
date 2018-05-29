package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Employee;
import com.company.company.model.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Service
public class EmployeeService extends GenericEntityService<Employee> {

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}
