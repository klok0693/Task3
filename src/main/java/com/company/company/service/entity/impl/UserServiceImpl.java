package com.company.company.service.entity.impl;

import com.company.company.model.entity.auth.User;
import com.company.company.repository.UserRepository;
import com.company.company.service.entity.UserService;
import com.company.company.util.NotNullByDefault;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@NotNullByDefault

@Service
@Transactional(isolation = REPEATABLE_READ)
public class UserServiceImpl extends GenericEntityService<User> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }


    @Override
    public User save(User user) {
        String hashPasword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPasword);

        return super.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = ((UserRepository) repository).findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


    @Override
    public Long deleteByUsername(String username) {
        return ((UserRepository) repository).deleteByUsername(username);
    }
}
