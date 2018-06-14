package com.company.company.model.entity.auth;

import com.company.company.model.entity.JpaEntity;
import com.company.company.util.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authorities")
@ToString(exclude = "authorities")

@Entity
@Table(name = "users")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements UserDetails, JpaEntity {

    @Id
    @Column(name = "userId")
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


    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    @Column(name = "email", nullable = true)
    private String email;


    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id")
    )
    @ManyToMany/*(fetch = EAGER, cascade = MERGE)*/
    private List<Authorities> authorities;


    @Column(name = "type", nullable = false)
    @JsonProperty(value = "type")
    private final String type = "user";
}
