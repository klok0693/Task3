package com.company.company.entity.auth;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@EqualsAndHashCode(exclude = "authorities")
@ToString(exclude = "authorities")
@Entity
@Table(name = "users")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class User implements UserDetails {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @NaturalId
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

    @ManyToMany(fetch = EAGER, cascade = PERSIST)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id"))
    private Set<Authorities> authorities;
}
