package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Entity
@NoArgsConstructor
@Table(name = "departments")
@Data
@ToString(exclude = {"company", "employees"})
@EqualsAndHashCode(exclude = {"company", "employees"}, callSuper = false)
public class Department implements JpaEntity {

    @Id @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id")
    private volatile Integer id;


    @Size(min = 3, max = 255)
    @Column(name = "name", nullable = false)
    private volatile String name;


    @ManyToOne(
            targetEntity = Company.class,
            cascade = MERGE,
            fetch = EAGER,
            optional = false
    )
    @JoinColumn(
            nullable = false,
            name = "company_id",
            referencedColumnName = "id"
    )
    @JsonBackReference(value = "companyDepartments")
    private volatile Company company;


    /** delete department -> delete all his employees */
    @OneToMany(
            targetEntity = Employee.class,
            mappedBy = "department",
            cascade = ALL,
            fetch = LAZY
    )
    @JsonManagedReference("departmentEmployees")
    private volatile Set<Employee> employees;


    @JsonProperty(value = "type")
    private final String type = "department";
}
