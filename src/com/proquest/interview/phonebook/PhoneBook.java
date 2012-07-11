package com.proquest.interview.phonebook;

import java.util.ArrayList;

/**
 * Interface declaring methods that needs to be implemented for PhoneBook
 */

public interface PhoneBook {
    public ArrayList findPersonByFirstName(String firstName);
    public ArrayList findPersonByLastName(String lastName);
    public ArrayList findPersonByPhoneNumber(String phoneNo);
    public ArrayList findPersonByAddress(String address);
	public void addPerson(Person newPerson);
    public Person findPerson(String firstName, String lastName);
    public void printPhoneBook();
}
