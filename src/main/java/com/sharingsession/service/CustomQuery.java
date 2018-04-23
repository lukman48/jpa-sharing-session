package com.sharingsession.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sharingsession.model.Employee;

@Service
public class CustomQuery {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long countEmployeeByName(String name){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Employee> root = countQuery.from(Employee.class);
		Predicate pCode = cb.equal(root.get("name"), name);
		
		countQuery.select(cb.count(root));
		em.createQuery(countQuery);
		 
		Long rowCount = em.createQuery(countQuery).getSingleResult();
		return rowCount;
	}
	
}
