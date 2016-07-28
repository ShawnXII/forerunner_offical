package com.forerunner.core.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.forerunner.foundation.domain.po.AbstractEntity;

public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends AbstractEntity<I>, I extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, I> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		return new MyRepositoryFactory<T,I>(em);
	}

	private static class MyRepositoryFactory<T extends AbstractEntity<I>, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager em;

		public MyRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.em = entityManager;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			 JpaEntityInformation<T, I> entityInformation = getEntityInformation((Class<T>) metadata.getDomainType());
			return new SimpleBaseRepository<T, I>(entityInformation, em);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return SimpleBaseRepository.class;
		}

	}

}
