package com.company.company.facade.impl;

import com.company.company.facade.CompanyFacade;
import com.company.company.model.entity.Company;
import com.company.company.service.entity.CompanyService;
import com.company.company.util.NotNullByDefault;
import org.springframework.stereotype.Component;

@NotNullByDefault

@Component
public class CompanyFacadeImpl extends GenericEntityFacade<Company> implements CompanyFacade {

    protected CompanyFacadeImpl(CompanyService service) {
        super(service);
    }
}
