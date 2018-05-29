package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Company;
import com.company.company.model.data.CompanyRepository;
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
public class CompanyService extends GenericEntityService<Company> {

    @Autowired
    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
