/**
 * 
 */
package com.hariram.factory;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * JUnit to test dynamic factory
 * 
 * @author hariram
 *
 */
public class DynamicFactoryTest {

	@Test
	public void testSuccess() {
		List<String> types = Arrays.asList("DB", "File");
		DynamicFactory factory = new DynamicFactory(types, Connection.class);
		
		Object obj = factory.getObject("DB");

		assertTrue(obj instanceof Connection);
	}
	
	@Test
	public void testFailureNoObject() {
		List<String> types = Arrays.asList("DB", "File");
		DynamicFactory factory = new DynamicFactory(types, Connection.class);
		
		Object obj = factory.getObject("DB1");
		
		assertTrue(!(obj instanceof Connection));
	}
	
	@Test
	public void testFailureNull() {
		DynamicFactory factory = new DynamicFactory(null, null);
		
		Object obj = factory.getObject("DB");
		
		assertTrue(!(obj instanceof Connection));
	}

}
