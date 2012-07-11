package com.proquest.interview.phonebook;

import com.proquest.interview.util.DatabaseUtil;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PhoneBookImplTest {

    private static boolean initiated = false;

    /**
     * To avoid creating database table more than once
     */
    private void prepareDB(){
        if(!initiated){
            DatabaseUtil.initDB();
            initiated = true;
        }
    }

    /**
     * Implemented Asked method for test
     */
	@Test
	public void shouldAddFindPerson() {
        prepareDB();
        PhoneBookImpl tester = new PhoneBookImpl();
        Person person =  new Person("Ritesh", "Basatwar","(615) 521-9802", "301 ROYAL OAKS BLVD, APT#2903, FRANKLIN, TN" );
        tester.addPerson(person);
        assertEquals(person,tester.findPerson("Ritesh", "Basatwar"));
	}

    /**
     * Implemented test for last name search
     */
    @Test
    public void findPersonByLastName(){
        prepareDB();
        PhoneBookImpl tester = new PhoneBookImpl();
        Person person =  new Person("Ritesh", "Basatwar","(615) 521-9802", "301 ROYAL OAKS BLVD, APT#2903, FRANKLIN, TN" );
        tester.addPerson(person);
        assertEquals(person,tester.findPersonByLastName("bas").get(0));
    }


    /**
     * Implemented test for First name search
     */
    @Test
    public void findPersonByFirstName(){
        prepareDB();
        PhoneBookImpl tester = new PhoneBookImpl();
        Person person =  new Person("Ritesh", "Basatwar","(615) 521-9802", "301 ROYAL OAKS BLVD, APT#2903, FRANKLIN, TN" );
        tester.addPerson(person);
        assertEquals(person,tester.findPersonByFirstName("esh").get(0));
    }




}
