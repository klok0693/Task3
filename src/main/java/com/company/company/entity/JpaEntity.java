package com.company.company.entity;

import com.company.company.NotNullByDefault;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

/*@SuppressWarnings("dmp")*/
@JsonTypeInfo(
        use = NAME,
        include = PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class,    name = "employee"),
        @JsonSubTypes.Type(value = Department.class,  name = "department"),
        @JsonSubTypes.Type(value = Company.class,     name = "company")
})
public interface JpaEntity {

    Integer getId();
    void setId(Integer id);
}
