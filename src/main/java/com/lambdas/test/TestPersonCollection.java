package com.lambdas.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lambdas.ui.Person;
import com.lambdas.ui.Person.Sex;

public class TestPersonCollection {

	public static void main (String []args) {
		//Inicializacion
		List<Person> people = new ArrayList();
		people.add(new Person("Iveth", LocalDate.of(1992, 1, 12), Sex.FEMALE, "gatito@gmail.com"));
		people.add(new Person("Axa", LocalDate.of(1991, 10, 13), Sex.MALE, "patito@gmail.com"));
		people.add(new Person("Dracula", LocalDate.of(1600, 10, 20), Sex.MALE, "dracula@gmail.com"));
		people.add(new Person("Selene", LocalDate.of(1820, 7, 3), Sex.FEMALE, "selene@gmail.com"));
		people.add(new Person("Mary", LocalDate.of(2008, 2, 11), Sex.FEMALE, "marilena@gmail.com"));
		people.add(new Person("Juanito", LocalDate.of(2009, 2, 11), Sex.MALE, "juanito@gmail.com"));
		people.add(new Person("Lorenz", LocalDate.of(1986, 12, 1), Sex.MALE, "xyz@gmail.com"));
		
		//Copia de listados para no alterar el original.
		List<Person> peopleNames = new ArrayList(people);
		List<Person> peopleAges = new ArrayList(people);
		List<Person> peopleAges2 = new ArrayList(people);
		
		Collections.sort(peopleAges, 
				(p1, p2) -> (int) p1.getAge() - p2.getAge());
		
		Collections.sort(peopleNames, 
				(p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
		System.out.println("Order by names");
		peopleNames.forEach(p -> System.out.println(p.printPerson()));
		
		System.out.println("Order by ages");
		peopleAges.forEach(p -> System.out.println(p.printPerson()));
		
		Collections.sort(peopleAges2, new Comparator<Person>() {
			@Override
			public int compare(Person arg0, Person arg1) {
				return arg0.getAge() - arg1.getAge();
			}
		});
		
		System.out.println("With clase anonima");
		peopleAges2.forEach(p-> System.out.println(p.printPerson()));
	}
}
