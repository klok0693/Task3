package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.Principal;
import com.company.company.entity.auth.User;
import com.company.company.model.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@NotNullByDefault

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
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

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
