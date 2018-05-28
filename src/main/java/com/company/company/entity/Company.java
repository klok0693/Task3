package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Entity(name = "Company")
@Table(name = "companies")
@Data
@ToString(exclude = "departments")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "departments", callSuper = false)
public class Company extends JpaEntity {
    @Id
    private volatile Integer id;
    private volatile String name;

    /*@ElementCollection(targetClass = Employee.class)
    @JsonManagedReference(value = "companyEmployees")
    private  Set<Employee> employees;*/

    @OneToMany
    //@ElementCollection(targetClass = Department.class)
    @JsonManagedReference(value = "companyDepartments")
    private Set<Department> departments;
}
