package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.Principal;
import com.company.company.entity.auth.User;
import com.company.company.model.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@NotNullByDefault

@Service
@Transactional(isolation = REPEATABLE_READ)
public class UserService implements UserDetailsService {

    private UserRepository repository;
    private UserEmailService emailService;

    @Autowired
    public UserService(UserRepository repository, UserEmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        /*return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthority());*/
        return new Principal(user);
    }

    public Long deleteByUsername(String username) {
        return repository.deleteByUsername(username);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void save(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);

        repository.save(user);
        emailService.sendSimpleMessage("usrmbtask@gmail.com", "Hi", "Hello, User!");
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
