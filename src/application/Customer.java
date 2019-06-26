// 
// Class 'Customer'
// Design Pattern: MVC - "Model View-Controller" version
// Role: Model class for customers data
//		 Used by the class 'LogWriter' to read customers data (names) from "db.csv"
//		 and then use it in the application (to be viewed on claims screen)
//

package application;

import java.util.ArrayList;

public class Customer {

	private String first_name;
	private String last_name;
	
	Customer(String first_name, String last_name)
	{
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	// Converts an array-list of 'Customers' to an array-list of names (Strings)
	public static ArrayList<String> customersArrayListToStrings(ArrayList<Customer> customers)
	{
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i< customers.size();i++)
			names.add(i, customers.get(i).getFirst_name() + " " + customers.get(i).getLast_name());
		return names;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}
}
