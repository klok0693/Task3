package com.company.company.facade.impl;

import com.company.company.facade.UserFacade;
import com.company.company.model.entity.auth.User;
import com.company.company.service.dto.EmailService;
import com.company.company.service.entity.UserService;
import com.company.company.util.NotNullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NotNullByDefault

@Component
public class UserFacadeImpl extends GenericEntityFacade<User> implements UserFacade {
    private EmailService emailService;

    @Autowired
    protected UserFacadeImpl(UserService service, EmailService emailService) {
        super(service);
        this.emailService = emailService;
    }


    @Override
    public User save(User user) throws Exception {

        super.save(user);
        emailService.sendMessageToAdmins(user.getUsername());

        return user;
    }
}
