package com.lambdas.interfaces;

import com.lambdas.ui.Person;

@FunctionalInterface
public interface CheckPerson {
	boolean test(Person p);
}
