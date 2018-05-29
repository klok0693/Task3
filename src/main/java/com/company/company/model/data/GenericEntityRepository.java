package com.company.company.model.data;

import com.company.company.entity.JpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by klok on 28.5.18.
 */
@Repository
public interface GenericEntityRepository<E extends JpaEntity> extends CrudRepository<E, Integer> {
}
