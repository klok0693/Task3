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
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@NotNullByDefault

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Authorities implements GrantedAuthority, JpaEntity {

    @Id
    @Column(name = "authorities_id")
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;


    @NaturalId
    @Column
    private String name;


    /*@JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "authorities_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )*/
    @ManyToMany(mappedBy = "authorities", fetch = EAGER, cascade = MERGE)
    private List<User> users;


    @Column(name = "type", nullable = false)
    @JsonProperty(value = "type")
    private final String type = "authority";


    @Override
    public String getAuthority() {
        return this.name;
    }
}
