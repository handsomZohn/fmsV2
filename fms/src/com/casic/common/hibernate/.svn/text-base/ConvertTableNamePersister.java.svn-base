package com.casic.common.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import com.casic.common.CustomSettionContext;

public class ConvertTableNamePersister extends SingleTableEntityPersister {

	public ConvertTableNamePersister(PersistentClass persistentClass,
			EntityRegionAccessStrategy cacheAccessStrategy,
			NaturalIdRegionAccessStrategy naturalIdRegionAccessStrategy,
			SessionFactoryImplementor factory, Mapping mapping)
			throws HibernateException {
		super(persistentClass, cacheAccessStrategy, naturalIdRegionAccessStrategy,
				factory, mapping);
	}
	@Override
	public String getTableName() {
		String tableName =  super.getTableName();
		if(CustomSettionContext.getRequestLocal()!=null){
			tableName += "_" + CustomSettionContext.getRequestLocal();
		}
		return tableName;
	}

	@Override
	protected String getTableName(int j) {
		String tableName =  super.getTableName(j);
		if(CustomSettionContext.getRequestLocal()!=null){
			tableName += "_" + CustomSettionContext.getRequestLocal();
		}
		return tableName;
	}

}
