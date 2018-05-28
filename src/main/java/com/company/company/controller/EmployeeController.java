package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Employee;
import com.company.company.model.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends GenericRestController<Employee> {

    public EmployeeController(EmployeeService service) {
        super(service);
    }
}
