package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Entity
@Table(name = "employees")
@Data
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department", callSuper = false)
@NoArgsConstructor
public class Employee implements JpaEntity {

    @Id @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id")
    private volatile Integer id;


    @Size(max = 255, min = 3)
    @Column(name = "firstName", nullable = false)
    private String firstName;


    @Size(max = 255, min = 3)
    @Column(name = "lastName", nullable = false)
    private String lastName;


    @CreatedDate
    @Column(name = "recruitment", nullable = false)
    @Temporal(TemporalType.DATE)
    private volatile Date recruitment;


    @ManyToOne(
            targetEntity = Department.class,
            cascade = PERSIST,
            fetch = EAGER,
            optional = false
    )
    @JoinColumn(
            nullable = false,
            name = "department",
            referencedColumnName = "id"
    )
    @JsonBackReference(value = "departmentEmployees")
    private volatile Department department;


    @JsonProperty(value = "type")
    private final String type = "employee";
}
