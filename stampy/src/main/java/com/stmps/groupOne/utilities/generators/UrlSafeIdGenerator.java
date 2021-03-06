package com.stmps.groupOne.utilities.generators;

import java.io.Serializable;
import java.util.Properties;

import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.stmps.groupOne.utilities.misc.CreateRandom;

public class UrlSafeIdGenerator implements IdentifierGenerator, Configurable {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String dbId = session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName();
		String dbTable = object.getClass().getAnnotation(Table.class).name();
		String queryTemplate = "select "+dbId+" from "+dbTable+" where "+dbId+" = '%s'";
		
		String newId = CreateRandom.urlSafe();
		String query = String.format(queryTemplate, newId);

		Integer resultSize = session.createSQLQuery(query).list().size();

        for (int i = 0; i < 3; i++) {
        	if(resultSize > 0) {
        		newId = CreateRandom.urlSafe();
        		query = String.format(queryTemplate, newId);
        		resultSize = session.createSQLQuery(query).list().size();
        	} else {
        		return newId;
        	}
			
		}
        return null;
	}

}
