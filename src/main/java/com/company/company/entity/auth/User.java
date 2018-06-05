package com.company.company.entity.auth;

import com.company.company.NotNullByDefault;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean isAccountNonExpired = true;
    @Column
    private boolean isAccountNonLocked = true;
    @Column
    private boolean isCredentialsNonExpired = true;
    @Column
    private boolean isEnabled =true;

    @Transient
    private Set<GrantedAuthority> roles;
}
