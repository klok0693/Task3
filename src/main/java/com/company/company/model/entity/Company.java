package com.company.company.model.entity;

import com.company.company.util.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Data
@NoArgsConstructor
@ToString(exclude = "departments")
@EqualsAndHashCode(exclude = "departments", callSuper = false)

@Entity
@Table(name = "companies")
public class Company implements JpaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE)
    private volatile Integer id;


    @Size(min = 3, max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private volatile String name;


    @OneToMany(
            targetEntity = Department.class,
            mappedBy = "company",
            cascade = ALL,
            fetch = LAZY
    )
    private Set<Department> departments;


    @Column(name = "type", nullable = false)
    @JsonProperty(value = "type")
    private final String type = "company";
}
