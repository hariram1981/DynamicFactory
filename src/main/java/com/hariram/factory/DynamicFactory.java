/**
 * 
 */
package com.hariram.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Factory class that creates objects as per types mentioned.
 * 1. Set types as list of types of sub-class objects
 * 2. Set return class name which is the super-class object
 * 3. Call getObject method with type mentioned
 *    - the super class will be returned with object of
 *      sub-class as per the type argument passed
 * 
 * @author hariram
 * @date 05-Nov-2014
 */
public class DynamicFactory {
	private List<String> types; 
	private Class<? extends Object> returnClassName;
	private static final Logger LOGGER = Logger.getLogger(DynamicFactory.class);
	
	/**
	 * Public constructor with list<string> and class as argument
	 */
	public DynamicFactory(List<String> types, Class<? extends Object> returnClassName) {
		this.types = types;
		this.returnClassName = returnClassName;
	}
	
	/**
	 * Return list of types (that are used in factory).
	 * 
	 * @return
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * Sets the list of types (to be used in factory).
	 * 
	 * @param types
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * Returns class of object to be created.
	 * 
	 * @return
	 */
	public Class<? extends Object> getReturnClassName() {
		return returnClassName;
	}

	/**
	 * Sets class of object to be created.
	 * 
	 * @param returnClassName
	 */
	public void setReturnClassName(Class<? extends Object> returnClassName) {
		this.returnClassName = returnClassName;
	}
	
	/**
	 * Return object created for the particular type mentioned
	 * 
	 * @param type
	 * @return object
	 */
	public Object getObject(String type) {
		LOGGER.info("DynamicFactory.getObject, type: " + type +", types: " + types + ", returnClassName: " + returnClassName);
		if(validate()){
			return new Object();
		}
		Package packageName = returnClassName.getPackage();
		final List<Object> objs = new ArrayList<Object>();
		types.stream()
			.filter(t -> t.equals(type))
			.forEach(t -> {
				try {
					String subClassNameStr = t;
					Class<? extends Object> subClassName = Class.forName(packageName.getName() + "." + subClassNameStr + returnClassName.getSimpleName());
					Object obj = subClassName.newInstance();
					objs.add(obj);
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
					LOGGER.error("DynamicFactory.getObject, message: " + e.getClass() + " " + e.getMessage());
				}
			});
		LOGGER.info("DynamicFactory.getObject, finished");
		
		return (objs.size() == 0) ? new Object() : objs.get(0);
	}
	/**
	 * Validate if the class variables are set or not.
	 * 
	 * @return boolean
	 */
	private boolean validate() {
		return types == null || returnClassName == null;
	}
}
