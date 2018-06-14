package com.company.company.controller.entity;

import com.company.company.facade.CompanyFacade;
import com.company.company.model.entity.Company;
import com.company.company.util.NotNullByDefault;
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
    public CompanyController(CompanyFacade service) {
        super(service);
    }
}
