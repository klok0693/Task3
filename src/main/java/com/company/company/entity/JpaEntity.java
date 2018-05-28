package com.company.company.entity;

import com.company.company.NotNullByDefault;

import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@SuppressWarnings("dmp")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class JpaEntity {

    @Id
    abstract Integer getId();
    abstract void setId(@Nullable Integer id);
}
