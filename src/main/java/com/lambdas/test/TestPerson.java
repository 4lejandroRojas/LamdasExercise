package com.lambdas.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.lambdas.impl.CheckPersonEligibleForSelectiveService;
import com.lambdas.interfaces.CheckPerson;
import com.lambdas.ui.Person;
import com.lambdas.ui.Person.Sex;

public class TestPerson {

	public static void main(String[] args) {

		// Inicializacion
		List<Person> people = new ArrayList<>();
		people.add(new Person("Iveth", LocalDate.of(1992, 1, 12), Sex.FEMALE, "gatito@gmail.com"));
		people.add(new Person("Axa", LocalDate.of(1991, 10, 13), Sex.MALE, "patito@gmail.com"));
		people.add(new Person("Dracula", LocalDate.of(1600, 10, 20), Sex.MALE, "dracula@gmail.com"));
		people.add(new Person("Selene", LocalDate.of(1820, 7, 3), Sex.FEMALE, "selene@gmail.com"));
		people.add(new Person("Mary", LocalDate.of(2008, 2, 11), Sex.FEMALE, "marilena@gmail.com"));
		people.add(new Person("Juanito", LocalDate.of(2009, 2, 11), Sex.MALE, "juanito@gmail.com"));
		people.add(new Person("Lorenz", LocalDate.of(1986, 12, 1), Sex.MALE, "xyz@gmail.com"));

		// Aproach 1. No es reutilizable
		printPersonsOlderThan(people, 30); // Personas arriba de 30
		printPersonsByGender(people, Sex.FEMALE); // Mujeres

		// Aproach 2. Más generico pero no demasiado.
		printPersonsWithinAgeRange(people, 30, 35);

		// Aproach 3. Con interface e implementacion para definir hombres en rango de
		// edad 20-35
		printPersons(people, new CheckPersonEligibleForSelectiveService());

		// Approach 4: Specify Search Criteria Code in an Anonymous Class.
		// Implementacion de clase anonima, no se requiere clase. Se puede mejorar
		printPersons(people, new CheckPerson() {
			public boolean test(Person p) {
				return p.getGender().equals(Sex.FEMALE) && p.getAge() >= 100;
			}
		});

		// Aproach 5. Specify Search Criteria Code with a Lambda Expression
		// Solo se necesita la interface CheckPerson.
		printPersons(people, (Person p) -> p.getGender().equals(Sex.MALE) && p.getAge() >= 10 && p.getAge() <= 20);

		// Aproach 6. No se necesita una interface, se utiliza un Predicate ya existente
		printPersonsWithPredicate(people,
				(Person p) -> p.getGender().equals(Sex.FEMALE) && p.getAge() >= 10 && p.getAge() <= 20);

		// Aproach 7. Solo se usa Predicate y no se requiere la invocacion printPerson.
		processPersons(people, (Person p) -> p.getAge() <= 15, p -> System.out.println(p.printPerson()));

		processPersonsWithFunction(people,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 30, p -> p.getEmailAddress(),
				email -> System.out.println(email));

		// Approach 8: Igual a anterior pero solo con genericos, mucho mas reutilizable
		processElements(people, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 30,
				p -> p.getEmailAddress(), email -> System.out.println(email));
		
		//Approach 9: Use Aggregate Operations That Accept Lambda Expressions as Parameters
		//No se requiere definicion de metodo.
		System.out.println("Just Aggregate Operations");
		people.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 30)
				.map(p -> p.getEmailAddress()).forEach(email -> System.out.println(email));
	}

	// ------------------------------------------------
	// Aproach 1. Create Methods That Search for Members That Match One
	// Characteristic
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		System.out.println("printPersonsOlderThan");
		for (Person p : roster) {
			if (p.getAge() >= age) {
				System.out.println(p.printPerson());
			}
		}
	}

	public static void printPersonsByGender(List<Person> roster, Sex gender) {
		System.out.println("printPersonsByGender");
		for (Person p : roster) {
			if (p.getGender().equals(gender)) {
				System.out.println(p.printPerson());
			}
		}
	}

	// -------------------------------------------------
	// Aproach 2.Create More Generalized Search Methods
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		System.out.println("printPersonsWithinAgeRange");
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				System.out.println(p.printPerson());
			}
		}
	}

	// -------------------------------------------------
	// Aproach 3. Specify Search Criteria Code in a Local Class
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		System.out.println("printPersons - with Criteria");
		for (Person p : roster) {
			if (tester.test(p)) {
				System.out.println(p.printPerson());
			}
		}
	}

	// Approach 6: Use Standard Functional Interfaces with Lambda Expressions
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		System.out.println("printPersons - with Predicate");
		for (Person p : roster) {
			if (tester.test(p)) {
				System.out.println(p.printPerson());
			}
		}
	}

	// Approach 7: Use Lambda Expressions Throughout Your Application
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
		System.out.println("printPersons - with Predicate and Consumer");
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}

	public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester,
			Function<Person, String> mapper, Consumer<String> block) {
		System.out.println("processPersonsWithFunction");
		for (Person p : roster) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

	// Approach 8: Use Generics More Extensively
	public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper,
			Consumer<Y> block) {
		System.out.println("processElements - Only Generics");
		for (X p : source) {
			if (tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}
}
