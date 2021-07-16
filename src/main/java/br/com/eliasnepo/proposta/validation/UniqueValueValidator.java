package br.com.eliasnepo.proposta.validation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.eliasnepo.proposta.exceptions.FieldMessage;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String>{

	@PersistenceContext
	private EntityManager entityManager;
	private String errorMessage;
	private String fieldName;
	private Class<?> klass;
	
	@Override
	public void initialize(UniqueValue ann) {
		errorMessage = ann.message();
		klass = ann.domainClass();
		fieldName = ann.fieldName();
	}
	
	@Override
	@Transactional
	public boolean isValid(String valueOfFieldName, ConstraintValidatorContext context) {
		Query query = entityManager
				.createQuery("select 1 from " + klass.getName() + " f where " + fieldName + " = :attribute")
				.setParameter("attribute", valueOfFieldName);
		
		List<FieldMessage> list = new ArrayList<>();
		if (query.getResultList().size() > 0) {
			list.add(new FieldMessage(fieldName, errorMessage));
		}
		return query.getResultList().size() == 0;
	}
}
