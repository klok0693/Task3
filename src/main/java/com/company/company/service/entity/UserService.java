package com.company.company.service.entity;

import com.company.company.model.entity.auth.User;
import com.company.company.util.NotNullByDefault;
import org.springframework.security.core.userdetails.UserDetailsService;

@NotNullByDefault
public interface UserService extends EntityService<User>, UserDetailsService {

    Long deleteByUsername(String username);
}
