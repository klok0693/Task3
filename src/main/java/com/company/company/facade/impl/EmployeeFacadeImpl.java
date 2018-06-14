package com.company.company.facade.impl;

import com.company.company.facade.EmployeeFacade;
import com.company.company.model.entity.Employee;
import com.company.company.service.entity.EmployeeService;
import com.company.company.util.NotNullByDefault;
import org.springframework.stereotype.Component;

@NotNullByDefault

@Component
public class EmployeeFacadeImpl extends GenericEntityFacade<Employee> implements EmployeeFacade {

    protected EmployeeFacadeImpl(EmployeeService service) {
        super(service);
    }
}
