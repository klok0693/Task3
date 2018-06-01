package com.company.company.entity;

import com.company.company.NotNullByDefault;
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

@Entity
@Table(name = "companies")

@Data
@ToString(exclude = "departments")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "departments", callSuper = false)
public class Company implements JpaEntity {

    @Id @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id")
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
    //@JsonManagedReference(value = "companyDepartments")
    private Set<Department> departments;


    @Column(name = "type", nullable = false)
    @JsonProperty(value = "type")
    private final String type = "company";
}
