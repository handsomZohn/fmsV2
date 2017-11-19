package com.casic.common.hibernate;

import org.hibernate.MappingException;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.mapping.Collection;
import org.hibernate.persister.collection.BasicCollectionPersister;
import org.hibernate.sql.Delete;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Update;

import com.casic.common.CustomSettionContext;

public class CollectionTableNamePersister extends BasicCollectionPersister{

	public CollectionTableNamePersister(Collection collection,
			CollectionRegionAccessStrategy cacheAccessStrategy,
			Configuration cfg, SessionFactoryImplementor factory)
			throws MappingException, CacheException {
		super(collection, cacheAccessStrategy, cfg, factory);
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
    protected String generateDeleteString() {
		
		Delete delete = new Delete()
				.setTableName( getTableName() )
				.addPrimaryKeyColumns( keyColumnNames );
		
		if ( hasWhere ) delete.setWhere( sqlWhereString );
		
		if ( getFactory().getSettings().isCommentsEnabled() ) {
			delete.setComment( "delete collection " + getRole() );
		}
		
		return delete.toStatementString();
	}

	/**
	 * Generate the SQL INSERT that creates a new row
	 */
	@Override
    protected String generateInsertRowString() {
		
		Insert insert = new Insert( getDialect() )
				.setTableName( getTableName() )
				.addColumns( keyColumnNames );
		
		if ( hasIdentifier) insert.addColumn( identifierColumnName );
		
		if ( hasIndex /*&& !indexIsFormula*/ ) {
			insert.addColumns( indexColumnNames, indexColumnIsSettable );
		}
		
		if ( getFactory().getSettings().isCommentsEnabled() ) {
			insert.setComment( "insert collection row " + getRole() );
		}
		
		//if ( !elementIsFormula ) {
			insert.addColumns( elementColumnNames, elementColumnIsSettable, elementColumnWriters );
		//}
		
		return insert.toStatementString();
	}

	/**
	 * Generate the SQL UPDATE that updates a row
	 */
	@Override
    protected String generateUpdateRowString() {
		
		Update update = new Update( getDialect() )
			.setTableName( getTableName() );
		
		//if ( !elementIsFormula ) {
			update.addColumns( elementColumnNames, elementColumnIsSettable, elementColumnWriters );
		//}
		
		if ( hasIdentifier ) {
			update.addPrimaryKeyColumns( new String[]{ identifierColumnName } );
		}
		else if ( hasIndex && !indexContainsFormula ) {
			update.addPrimaryKeyColumns( ArrayHelper.join( keyColumnNames, indexColumnNames ) );
		}
		else {
			update.addPrimaryKeyColumns( keyColumnNames );
			update.addPrimaryKeyColumns( elementColumnNames, elementColumnIsInPrimaryKey, elementColumnWriters );
		}
		
		if ( getFactory().getSettings().isCommentsEnabled() ) {
			update.setComment( "update collection row " + getRole() );
		}
		
		return update.toStatementString();
	}

	/**
	 * Generate the SQL DELETE that deletes a particular row
	 */
	@Override
    protected String generateDeleteRowString() {
		
		Delete delete = new Delete()
			.setTableName( getTableName() );
		
		if ( hasIdentifier ) {
			delete.addPrimaryKeyColumns( new String[]{ identifierColumnName } );
		}
		else if ( hasIndex && !indexContainsFormula ) {
			delete.addPrimaryKeyColumns( ArrayHelper.join( keyColumnNames, indexColumnNames ) );
		}
		else {
			delete.addPrimaryKeyColumns( keyColumnNames );
			delete.addPrimaryKeyColumns( elementColumnNames, elementColumnIsInPrimaryKey, elementColumnWriters );
		}
		
		if ( getFactory().getSettings().isCommentsEnabled() ) {
			delete.setComment( "delete collection row " + getRole() );
		}
		
		return delete.toStatementString();
	}

}
