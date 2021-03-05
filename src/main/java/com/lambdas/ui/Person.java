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
}
