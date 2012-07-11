package com.proquest.interview.phonebook;

/**
 * POJO for Person Class
 */

public class Person {
	private String firstName;
    private String lastName;
	private String phoneNumber;
	private String address;

    /**
     *
     * @param firstName : FirstName
     * @param lastName : LastName
     * @param phoneNumber : Phone Number
     * @param address : Address
     */
    public Person(String firstName, String lastName, String phoneNumber, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Method to return First Name
     * @return String FirstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to set FirstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to return LastName
     * @return String LastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Method to set LastName
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Method to set PhoneNumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    /**
     * Method to set Address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Overriding default toString Method.
     * @return String Complete Text for Person Object
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append(" ||| Phone: ");
        sb.append(phoneNumber);
        sb.append(" ||| Address: ");
        sb.append(address);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(person.phoneNumber) : person.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }


}
