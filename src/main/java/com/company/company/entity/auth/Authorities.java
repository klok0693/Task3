package com.company.company.entity.auth;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Authorities implements GrantedAuthority {

    @Id @Column(name = "authorities_id")
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @NaturalId
    @Column
    private String name;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "authorities_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
