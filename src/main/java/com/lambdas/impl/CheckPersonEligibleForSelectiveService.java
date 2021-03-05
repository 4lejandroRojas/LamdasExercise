package com.lambdas.impl;

import com.lambdas.interfaces.CheckPerson;
import com.lambdas.ui.Person;
import com.lambdas.ui.Person.Sex;

public class CheckPersonEligibleForSelectiveService implements CheckPerson{

	public boolean test(Person p) {
		return p.getGender().equals(Sex.MALE) && 
				p.getAge() <=35 &&
				p.getAge() >=20;
	}

}
