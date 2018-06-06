package com.company.company.entity.auth;

import com.company.company.NotNullByDefault;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "accountNonExpired")
    private boolean isAccountNonExpired = true;

    @Column(name = "accountNonLocked")
    private boolean isAccountNonLocked = true;

    @Column(name = "credentialsNonExpired")
    private boolean isCredentialsNonExpired = true;

    @Column(name = "enabled")
    private boolean isEnabled =true;

    @ManyToMany(targetEntity = Authorities.class, fetch = EAGER)
    private Set<Authorities> authorities;
}
