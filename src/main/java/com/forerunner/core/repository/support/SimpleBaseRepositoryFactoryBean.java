package com.forerunner.core.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.core.search.annotation.SearchableQuery;
import com.forerunner.core.search.callback.SearchCallback;
import com.forerunner.foundation.domain.po.AbstractEntity;

/**
 * 基础Repostory简单实现 factory bean
 * 请参考 spring-data-jpa-reference [1.4.2. Adding custom behaviour to all repositories]
 * <p>Version: 1.0
 */
public class SimpleBaseRepositoryFactoryBean<R extends JpaRepository<M, ID>, M extends AbstractEntity<ID>, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, M, ID> {

    public SimpleBaseRepositoryFactoryBean() {
    }

    @SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new SimpleBaseRepositoryFactory(entityManager);
    }
}

class SimpleBaseRepositoryFactory<M extends AbstractEntity<ID>, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public SimpleBaseRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
	protected Object getTargetRepository(RepositoryMetadata metadata) {
        Class<?> repositoryInterface = metadata.getRepositoryInterface();

        if (isBaseRepository(repositoryInterface)) {

            JpaEntityInformation<M, ID> entityInformation = getEntityInformation((Class<M>) metadata.getDomainType());
            SimpleBaseRepository<M, ID> repository = new SimpleBaseRepository<M, ID>(entityInformation, entityManager);

            SearchableQuery searchableQuery = AnnotationUtils.findAnnotation(repositoryInterface, SearchableQuery.class);
            if (searchableQuery != null) {
                String countAllQL = searchableQuery.countAllQuery();
                if (!"".equals(countAllQL) && countAllQL != null) {
                    repository.setCountAllQL(countAllQL);
                }
                String findAllQL = searchableQuery.findAllQuery();
                if (!"".equals(findAllQL) && findAllQL != null) {
                    repository.setFindAllQL(findAllQL);
                }
                Class<? extends SearchCallback> callbackClass = searchableQuery.callbackClass();
                if (callbackClass != null && callbackClass != SearchCallback.class) {
                    repository.setSearchCallback(BeanUtils.instantiate(callbackClass));
                }

                repository.setJoins(searchableQuery.joins());

            }

            return repository;
        }
        return super.getTargetRepository(metadata);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isBaseRepository(metadata.getRepositoryInterface())) {
            return SimpleBaseRepository.class;
        }
        return super.getRepositoryBaseClass(metadata);
    }

    private boolean isBaseRepository(Class<?> repositoryInterface) {
        return BaseRepository.class.isAssignableFrom(repositoryInterface);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
        return super.getQueryLookupStrategy(key);
    }
}
