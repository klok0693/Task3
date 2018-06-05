package com.company.company.model.data;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@NotNullByDefault

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
