package de.goldmann.texter.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author ronded
 *
 * @param <T> the entity class
 * @param <PK> the class of the id field
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model
	 * classes correctly implement the hashcode/equals methods
	 * </p>
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct();

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	void remove(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	T save(T object);
	
	/**
	 * remove all objects from entitymanager cache
	 * 
	 */
	void clearCache();
}