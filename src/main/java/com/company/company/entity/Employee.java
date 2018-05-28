package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
public class Employee extends JpaEntity {
    @Id
    private volatile Integer  id;
    private volatile String   firstName,
                              lastName;
    @Temporal(TemporalType.DATE)
    private volatile Date     recruitment;

    @ManyToOne
    @JsonBackReference(value = "departmentEmployees")
    private volatile Department department;
}
