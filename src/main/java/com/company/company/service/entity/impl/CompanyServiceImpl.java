package com.company.company.service.entity.impl;

import com.company.company.model.entity.Company;
import com.company.company.repository.CompanyRepository;
import com.company.company.service.entity.CompanyService;
import com.company.company.util.NotNullByDefault;
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
public class CompanyServiceImpl extends GenericEntityService<Company> implements CompanyService {

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        super(repository);
    }
}
