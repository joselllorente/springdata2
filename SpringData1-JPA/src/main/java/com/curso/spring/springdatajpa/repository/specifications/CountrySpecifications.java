package com.curso.spring.springdatajpa.repository.specifications;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.curso.spring.springdatajpa.model.Country;

public abstract class CountrySpecifications
{
	public static Specification<Country> searchByName(final String name) {
		 return new Specification<Country>() {

			@Override
			public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
			{
				return criteriaBuilder.equal(root.get("name"), name);
			}
		};
	  }
}
