package com.lambdas.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lambdas.ui.Person;
import com.lambdas.ui.Person.Sex;

public class TestPersonStream {

	public static void main(String args[]) {
		// Inicializacion
		List<Person> people = new ArrayList<>();
		people.add(new Person("Iveth", LocalDate.of(1992, 1, 12), Sex.FEMALE, "gatito@gmail.com"));
		people.add(new Person("Axa", LocalDate.of(1991, 10, 13), Sex.MALE, "patito@gmail.com"));
		people.add(new Person("Dracula", LocalDate.of(1600, 10, 20), Sex.MALE, "dracula@gmail.com"));
		people.add(new Person("Selene", LocalDate.of(1820, 7, 3), Sex.FEMALE, "selene@gmail.com"));
		people.add(new Person("Mary", LocalDate.of(2008, 2, 11), Sex.FEMALE, "marilena@gmail.com"));
		people.add(new Person("Juanito", LocalDate.of(2009, 2, 11), Sex.MALE, "juanito@gmail.com"));
		people.add(new Person("Lorenz", LocalDate.of(1986, 12, 1), Sex.MALE, "xyz@gmail.com"));
		
		//Predicate (filter) doesn't allow reference methods because of Functional Interface.
		//If reference method of 'sout' is it necessary then it is necessary map because  it doesn't allow parameters.
		people.stream().filter(person -> person.getGender()== Sex.FEMALE).forEach(person -> System.out.println(person.printPerson()));
		people.stream().filter(person -> person.getGender()== Sex.FEMALE).map(Person::printPerson).forEach(System.out::println);

		//Just an example of parallel Stream
		people.parallelStream().forEach(p -> System.out.println(p.printPerson()));
		
		//Function (map) allows lambda or reference methods because of itself.
		people.stream().map(p-> p.printPerson()).forEach(System.out::println);
		people.stream().map(Person::printPerson).forEach(System.out::println);
		
		int ageSum = people.stream().filter(person -> person.getGender()== Sex.FEMALE).mapToInt(person -> person.getAge()).sum();
		int ageSum2 = people.stream().filter(person -> person.getGender()== Sex.FEMALE).mapToInt(Person::getAge).sum();
		System.out.println("Age of sum: "+ageSum);	
		System.out.println("-----------------------------");
		


		
	}
}
