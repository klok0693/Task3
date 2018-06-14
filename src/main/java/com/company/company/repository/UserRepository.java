package com.company.company.repository;

import com.company.company.model.entity.auth.User;
import com.company.company.util.NotNullByDefault;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@NotNullByDefault

@Repository
public interface UserRepository extends GenericEntityRepository<User> {

    User findByUsername(String username);

    Long deleteByUsername(String username);

    @Query(nativeQuery = true,
            value = "SELECT u.email FROM users u, user_authorities ua WHERE u.user_id=ua.user_id AND ua.authorities_id=2")
    List<String> getAdminsEmails();
}
