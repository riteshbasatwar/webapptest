package com.proquest.interview.phonebook;

import java.util.ArrayList;


import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {

	//public List people; // commented as not using, not removing as you might have expected some use of this object.
	

	public static void main(String[] args) {

        DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

        Person person;
        ArrayList arrayList;

        PhoneBookImpl ph = new PhoneBookImpl();

        // TODO: print the phone book out to System.out
        ph.printPhoneBook();


        /** This was asked functionality */
        ph.addPerson(new Person("John", "Smith","(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI" ));
        ph.addPerson(new Person("Cynthia", "Smith","(824) 128-8758", " 875 Main St, Ann Arbor, MI" ));

        // TODO: print the phone book out to System.out
        System.out.println("\n\n=========================== Printing again after adding above rows ============================\n\n");

        // TODO: print the phone book out to System.out
        ph.printPhoneBook();


		/** TODO: find Cynthia Smith and print out just her entry [This will return only the first entry found with her name,
		 other entries are ignored. I did not want to change method signature so behaviour is matching to signature]*/

        person = ph.findPerson("Cynthia", "Smith");
        System.out.println(person);

        // TODO: insert the new person objects into the database

        ph.addPerson(new Person("John", "Grishm","(615) 525-5545", "301 ROYAL OAKS BLVD, FRANKLIN, TN" ));
        ph.addPerson(new Person("Alex", "Mason","(615) 443-4534", "648 GRASSMERE PARK, NASHVILLE, TN" ));
        ph.addPerson(new Person("Barb", "Wirsen","(816) 888-4545", "123 MAIN ST, KANSAS CITY, KC" ));
        ph.addPerson(new Person("Alex", "Corolvich","(615) 443-4534", "656 PARK STREET, ST. LOISE, MO" ));

        // Additional method implementations :
        System.out.println("\n\n===================== Printing only matching first name entries ==============================");
        arrayList = ph.findPersonByFirstName("ALEX");
        ph.printArrayList(arrayList);

        System.out.println("\n\n===================== Printing only matching partial last name entries =======================");
        arrayList = ph.findPersonByLastName("smith");
        ph.printArrayList(arrayList);

        System.out.println("\n\n===================== Printing from specific states ==========================================");
        arrayList = ph.findPersonByAddress("tn");
        ph.printArrayList(arrayList);

        System.out.println("\n\n===================== Printing partial phone number matching==================================");
        arrayList = ph.findPersonByPhoneNumber("454");
        ph.printArrayList(arrayList);

    }

    /**
     * Search and returns only matching FirstName and LastName combination
     * @param firstName FirstName
     * @param lastName  Last Name
     * @return com.proquest.interview.phonebook.Person First from resultset or null if no records found
     */
    @Override
    public Person findPerson(String firstName, String lastName) {
        Person person = DatabaseUtil.getPerson(firstName, lastName);
        if(person == null){
            System.out.println("Phone Entry not found for this person");
        }
        return person;
    }

    /**
     * Search and returns results based on full or partial First Name, also ignores the case
     * @param firstName  Full or Partial FirstName
     * @return ArrayList List of com.proquest.interview.phonebook.Person matching to this criteria
     */
    @Override
    public ArrayList findPersonByFirstName(String firstName) {
        return DatabaseUtil.getPersonByFirstName(firstName);
    }

    /**
     * Search and returns results based on full or partial Last Name, also ignores the case
     * @param lastName  Full or Partial LastName
     * @return ArrayList List of com.proquest.interview.phonebook.Person matching to this criteria
     */

    @Override
    public ArrayList findPersonByLastName(String lastName) {
        return DatabaseUtil.getPersonByLastName(lastName);
    }

    /**
     * Search and returns results based on full or partial Phone Number
     * @param phoneNo  Full or Partial Phone Number
     * @return ArrayList List of com.proquest.interview.phonebook.Person matching to this criteria
     */
    @Override
    public ArrayList findPersonByPhoneNumber(String phoneNo) {
        return DatabaseUtil.getPersonByPhoneNo(phoneNo);
    }

    /**
     * Search and returns results based on full or partial address, also ignores case
     * @param address  Full or Partial Address
     * @return ArrayList List of com.proquest.interview.phonebook.Person matching to this criteria
     */
    @Override
    public ArrayList findPersonByAddress(String address) {
        return DatabaseUtil.getPersonByAddress(address);
    }

    /**
     * Adds a Person into databse
     * @param newPerson com.proquest.interview.phonebook.Person
     */
    @Override
    public void addPerson(Person newPerson) {
        DatabaseUtil.addPerson(newPerson);
    }

    /**
     * Prints the phonebook on console
     */
    @Override
    public void printPhoneBook(){
        System.out.println("================================== Phone Book ===================================================");
        printArrayList(DatabaseUtil.getPhoneBook());
        System.out.println("=================================================================================================");
    }

    /**
     * Simple method to print ArrayList
     * @param list ArrayList
     */
    public void printArrayList(ArrayList list){

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
