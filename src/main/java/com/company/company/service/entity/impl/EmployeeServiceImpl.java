package com.company.company.service.entity.impl;

import com.company.company.model.entity.Employee;
import com.company.company.service.entity.EmployeeService;
import com.company.company.util.NotNullByDefault;
import com.company.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Service
@Transactional(propagation = REQUIRED)
public class EmployeeServiceImpl extends GenericEntityService<Employee> implements EmployeeService {

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }
}
