package com.company.company.entity.auth;

import com.company.company.NotNullByDefault;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@NoArgsConstructor
@Entity
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(targetEntity = User.class, fetch = EAGER)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
