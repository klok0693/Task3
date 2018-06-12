package com.company.company.model.data;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@NotNullByDefault

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    Long deleteByUsername(String username);

    @Query(nativeQuery = true,
            value = "SELECT u.email FROM users u, user_authorities ua WHERE u.user_id=ua.user_id AND ua.authorities_id=2")
    List<String> getAdminsEmails();
}
