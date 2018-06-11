package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Company;
import com.company.company.model.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping(value = "/company")
public class CompanyController extends GenericRestController<Company> {

    @Autowired
    public CompanyController(CompanyService service) {
        super(service);
    }
}
