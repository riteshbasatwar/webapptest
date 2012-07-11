package com.proquest.interview.util;

import com.proquest.interview.phonebook.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class is just a utility class, you should not have to change anything here
 * @author rconklin
 */
public class DatabaseUtil {

    /**
     * Initial method provided by author, did not modify except explicitly closing connection object.
     */
    public static void initDB() {
        //Moved connection and statement declaration to top so that they can be accessed by finally method
        Connection cn = null;
        Statement stmt = null;
		try {
			cn = getConnection();
			stmt = cn.createStatement();
			stmt.execute("CREATE TABLE PHONEBOOK (FIRSTNAME varchar(255), LASTNAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
			stmt.execute("INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME ,PHONENUMBER, ADDRESS) VALUES('Chris', 'Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
			stmt.execute("INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME ,PHONENUMBER, ADDRESS) VALUES('Dave', 'Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
			cn.commit();
			cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally{ // Added finally block to close statement and connection objects explicitly.
            try{
                if(stmt != null)stmt.close();
                if(cn != null)cn.close();
            }catch(SQLException ex){}
        }
		
	}

    /**
     * Initial method that author provided, did not change as it does not need any rectification
     * @return Connection Object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
	}

    /**
     * Returns List of persons found with matching partial First Name, not case sensitive
     * @param firstName String PartialFirstName
     * @return ArrayList
     */
    public static ArrayList getPersonByFirstName(String firstName){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM PHONEBOOK WHERE LOWER(FIRSTNAME) LIKE '%");
        sb.append(firstName.toLowerCase());
        sb.append("%'");
        return retrivePersonData(sb.toString());
    }

    /**
     *  Returns List of persons found with matching partial Last Name, not case sensitive
     * @param lastName String PartialLastName
     * @return ArrayList
     */
    public static ArrayList getPersonByLastName(String lastName){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM PHONEBOOK WHERE LOWER(LASTNAME) LIKE '%");
        sb.append(lastName.toLowerCase());
        sb.append("%'");
        return retrivePersonData(sb.toString());
    }

    /**
     * Returns list of persons with matching patial phone number.
     * @param phoneNo partial or fill Phone Number
     * @return ArrayList
     */
    public static ArrayList getPersonByPhoneNo(String phoneNo){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM PHONEBOOK WHERE PHONENUMBER  LIKE '%");
        sb.append(phoneNo);
        sb.append("%'");
        return retrivePersonData(sb.toString());
    }

    /**
     * Returns list of matching persons with provided filter to match address, is not case sensitive
     * @param address Partial or full address.
     * @return ArrayList
     */
    public static ArrayList getPersonByAddress(String address){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM PHONEBOOK WHERE LOWER(ADDRESS) LIKE '%");
        sb.append(address.toLowerCase());
        sb.append("%'");
        return retrivePersonData(sb.toString());
    }

    /**
     * Returns exactly matching person from the list, returns only first record
     * @param firstName Full First Name
     * @param lastName Full Last Name
     * @return Person object
     */
    public static Person getPerson(String firstName, String lastName){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM PHONEBOOK WHERE FIRSTNAME = '");
        sb.append(firstName);
        sb.append("' AND LASTNAME = '");
        sb.append(lastName);
        sb.append("'");
        ArrayList a =  retrivePersonData(sb.toString());
        if(a.size() >= 1)
            return (Person) a.get(0);
        else
            return null;
    }

    /**
     * Returns complete phone book
     * @return
     */
    public static ArrayList getPhoneBook(){
        return retrivePersonData("SELECT * FROM PHONEBOOK");
    }

    /**
     * Common method that will be used to fetch records from database
     * @param sqlStatement A Valid Select SQL statement on PhoneBook table
     * @return ArrayList of Person Objects.
     */

    public static ArrayList retrivePersonData(String sqlStatement){
        Connection cn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            cn = getConnection();
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
            while(rs.next()){
                list.add(new Person(rs.getString("FIRSTNAME"), rs.getString("LASTNAME") ,
                        rs.getString("PHONENUMBER"), rs.getString("ADDRESS")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{ // Added finally block to close statement and connection objects explicitly.
            try{
                if(rs != null) rs.close();
                if(stmt != null)stmt.close();
                if(cn != null)cn.close();
            }catch(SQLException ex){}
        }
        return list;
    }

    /**
     *  Adds an person entry into database.
     * @param person
     */
    public static void addPerson(Person person){
        String sqlStmt = createStringFromPerson(person);
        Connection cn = null;
        Statement stmt = null;
        try {
            cn = getConnection();
            stmt = cn.createStatement();
            stmt.execute(sqlStmt);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{ // Added finally block to close statement and connection objects explicitly.
            try{
                if(stmt != null)stmt.close();
                if(cn != null)cn.close();
            }catch(SQLException ex){}
        }

    }

    /**
     * Private Method to create SQL string from Person Object to insert into database.
     * @param person
     * @return
     */
    private static String createStringFromPerson(Person person){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME ,PHONENUMBER, ADDRESS) VALUES('");
        sb.append(person.getFirstName());
        sb.append("','");
        sb.append(person.getLastName());
        sb.append("','");
        sb.append(person.getPhoneNumber());
        sb.append("','");
        sb.append(person.getAddress());
        sb.append("')");
        return sb.toString();
    }
}
