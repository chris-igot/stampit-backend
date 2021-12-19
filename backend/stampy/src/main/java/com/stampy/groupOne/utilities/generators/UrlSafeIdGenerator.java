package com.stampy.groupOne.utilities.generators;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

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

import com.stampy.groupOne.utilities.random.RandGenerator;

public class UrlSafeIdGenerator implements IdentifierGenerator, Configurable {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String dbId = session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName();
		String dbTable = object.getClass().getAnnotation(Table.class).name();
		String queryTemplate = "select "+dbId+" from "+dbTable+" where "+dbId+" = \"%s\";";
//		String query = String.format("select %s from %s where;", dbId, dbTable);
		
		String newId = RandGenerator.urlSafe();
		String query = String.format(queryTemplate, newId);
        Integer resultSize = jdbcTemplate.queryForList(query).size();

        System.out.println("New ID");
        System.out.println(resultSize);
        newId = "newidcopy";
        for (int i = 0; i < 3; i++) {
        	if(resultSize > 0) {
        		System.out.println("BAD ID");
        		newId = RandGenerator.urlSafe();
        		query = String.format(queryTemplate, newId);
        		resultSize = jdbcTemplate.queryForList(query).size();
        	} else {
        		System.out.println(newId);
        		return newId;
        	}
			
		}

        return newId;
	}

}
