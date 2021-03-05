package com.lambdas.ui;

import java.time.LocalDate;
import java.time.Period;

public class Person {
	public enum Sex{
		MALE, FEMALE
	}
	
	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;
	
	public int getAge() {
		return Period.between(birthday, LocalDate.now()).getYears();
	}
	
	public Sex getGender() {
		return gender;
	}

	
	public String getName() {
		return name;
	}

	public Person(String name, LocalDate birthday, Sex gender, String emailAdress) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.emailAddress = emailAdress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String printPerson() {
		return "Person [name=" + name + ", birthday=" + birthday + ", gender=" + gender + ", emailAddress=" + emailAddress
				+ ", age: "+getAge()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	
	
}
