package test.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import thewebsemantic.TypeWrapper;

public class TestTypeWrapper {

	
	@Test
	public void id() {
		TypeWrapper t = TypeWrapper.wrap(Person.class);
		String uri = "http://person/1";
		Person p = new Person(uri);	
		
		//uri
		assertEquals(t.id(p), uri);
		
		//hashcode
		Apple a = new Apple();
		String id = TypeWrapper.wrap(Apple.class).id(a);
		assertEquals(id, a.hashCode() + "");
		
		id = TypeWrapper.getId(a);
		assertEquals(id, a.hashCode() + "");
		
		User u = new User("jenabean");
		id = TypeWrapper.getId(u);
		assertEquals(id, "jenabean");
	}
}
