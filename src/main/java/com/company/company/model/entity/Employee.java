package com.company.company.model.entity;

import com.company.company.util.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Data
@NoArgsConstructor
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department", callSuper = false)

@Entity
@Table(name = "employees")
public class Employee implements JpaEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = SEQUENCE)
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
            fetch = EAGER,
            optional = false
    )
    @JoinColumn(
            nullable = false,
            name = "department",
            referencedColumnName = "id"
    )
    @JsonIgnoreProperties({"name", "company","employees"})
    private volatile Department department;


    @JsonProperty(value = "type")
    private final String type = "employee";
}
