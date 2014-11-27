DynamicFactory
==============

Class that implements factory pattern dynamically (dynamically creating classes using reflection). Take a look at the JUnit test case that has been written as to usage.

Classes
----------
###1. com.hariram.factory.DynamicFactory
  - Class that handles factories in dynamic.
 
####Data Members
1. List<String> types - types of classes that are created by the factory.
2. Class returnClassName - the class or interface that is returned back after factory has created objects. 

####Member functions

1. void setReturnClassName(Class<? extends Object)
  - Sets the class that is returned after factory creation.
 
2. void setTypes(List<String>)
  - Sets the list of types that are supported for factory creation.
 
3. Class<? extends Object> getReturnClassName
  - Returns the class that is returned (as generic) after factory creation.

4. List<String> getTypes()
  - Returns the list of types that are supported for factory creation.
 
5. Object getObject(String)
  - Implements factory pattern and creation returns the generic class for the type passed.
  - Before this method is used, the types that are available for creation and the generic that is returned after creation have to be set using setTypes and setReturnClassName OR using the parameterized constructor.
  
6. DynamicFactory(List<String>, Class<? extends Object)
  - parameterized constructor that sets the names of the types of objects that are created by factory and the return class.
  
Usage
----------
- Usage can be checked in the JUnit test case that has been written for it but below is a sample:

		List<String> types = Arrays.asList("DB", "File");
		DynamicFactory factory = new DynamicFactory(types, Connection.class);
		Object obj = factory.getObject("DB");
		System.out.println(obj instanceof Connection);

- In above, Connection is the return class and the objects created are DBConnection and FileConnection. Hence the types of connection classes to be created by factory are "DB" and "File".

Dynamically creates classes based on the factory pattern for a set of classes (the types of classes are mentioned as an enum paraaeter and the base class is passed as parameter)

License
==========
Copyright (c) 2014 GitHub, Inc. See the LICENSE file for license rights and limitations (GNU GPL v2.0)
