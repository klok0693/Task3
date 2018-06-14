package com.company.company.repository;

import com.company.company.model.entity.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by klok on 28.5.18.
 */
@Repository
public interface GenericEntityRepository<E extends JpaEntity> extends JpaRepository<E, Integer> {
}
