package com.vineet.spring.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.vineet.spring.configuration.ApplicationConfig;
import com.vineet.spring.dao.PersonDAOImpl;
import com.vineet.spring.model.Person;

public class DriverMain {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		PersonDAOImpl personDAO = context.getBean(PersonDAOImpl.class);

		System.out.println("List of person is:");

		for (Person p : personDAO.getAllPersons()) {
			System.out.println(p);
		}

		System.out.println("\nGet person with ID 2");

		Person personById = personDAO.getPersonById(2L);
		System.out.println(personById);

		System.out.println("\nCreating person: ");
		Person person = new Person(6L, 39, "Vineet", "Talashi");
		System.out.println(person);
		personDAO.createPerson(person);
		System.out.println("\nList of person is:");

		for (Person p : personDAO.getAllPersons()) {
			System.out.println(p);
		}

		System.out.println("\nDeleting person with ID 2");
		//personDAO.deletePerson(personById);

		System.out.println("\nUpdate person with ID 4");

		Person pperson = personDAO.getPersonById(4L);
		pperson.setLastName("CHANGED");
		personDAO.updatePerson(pperson);

		System.out.println("\nList of person is:");
		for (Person p : personDAO.getAllPersons()) {
			System.out.println(p);
		}

		((AbstractApplicationContext) context).close();
	}
}
