package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Company;
import com.company.company.model.data.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Service
@RequestMapping(value = "/company")
public class CompanyService extends GenericEntityService<Company> {

    @Autowired
    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
