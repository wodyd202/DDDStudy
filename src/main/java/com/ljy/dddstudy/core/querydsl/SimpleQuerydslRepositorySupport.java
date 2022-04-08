package com.ljy.dddstudy.core.querydsl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
abstract public class SimpleQuerydslRepositorySupport<T> extends QuerydslRepositorySupport {
    protected SimpleQuerydslRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    public void save(T obj) {
        EntityManager entityManager = getEntityManager();
        if(entityManager.contains(obj)) {
            entityManager.merge(obj);
            return;
        }
        entityManager.persist(obj);
    }
}
