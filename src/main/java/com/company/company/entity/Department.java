package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Entity(name = "Department")
@Table(name = "departments")
@Data
@ToString(exclude = {"company", "employees"})
@EqualsAndHashCode(exclude = {"company", "employees"}, callSuper = false)
public class Department extends JpaEntity {
    @Id
    private volatile Integer id;
    private volatile String  Name;

    @ManyToOne
    @JsonBackReference(value = "companyDepartment")
    private volatile Company company;

    //@ElementCollection(targetClass = Employee.class)
    @OneToMany
    @JsonManagedReference(value = "departmentEmployees")
    private volatile Set<Employee> employees;
}
