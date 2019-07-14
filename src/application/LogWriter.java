/* 
 Class 'LogWriter'
 Design Pattern: Singelton - "Early Instantiation" version, MVC - "Model View-Controller" version
 Role: Singelton class to write sales and claims to "logger.txt"
		 Also writes/reads to/from DB ('db.csv')
		 Extends the controllers 'ControllerClaimsScreen' and 'ControllerSalesScreen' as they use it to manage I/O.
*/

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.chart.PieChart.Data;

public class LogWriter {

	private final static LogWriter _instance = new LogWriter();

	private LogWriter() 
	{
		// Constructing an only instance deceleration		
	}
	
	public static LogWriter getInstance()
	{
		return _instance;
	}
	
	// Writes the details of a new sale to "logger.txt"
	public void saveSaleToLoggerFile(String agent_name, String insurance_type, String due_date, 
										 String customer_first_name, String customer_last_name)
	{
		FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("logger.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.write("Sale:\n" +
            		"Agent: " + agent_name + "		" +
            		"Insurance type: " + insurance_type + "		" +
            		"Due date: " + due_date + "		" +
            		"Customer first name: " + customer_first_name + "		" +    
    				"Customer last name: " + customer_last_name + "\n");  
            pw.flush();
        }
        catch (IOException io) 
        {
        	io.printStackTrace();
        }
        finally 
        {
            try 
            {
                pw.close();
                bw.close();
                fw.close();
            } 
            catch (IOException io) 
            {
            	io.printStackTrace();
            }
        }
        // Also save to 'db.csv'
        saveSaleToDBFile(agent_name, insurance_type, due_date,
        				customer_first_name, customer_last_name);
	}
	
	// Writes the details of a new claim to "logger.txt"
	public void saveClaimToLoggerFile(String agent_name, String customer_name,
									 String insurance_type, String comments)
	{
		FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("logger.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.write("Claim:\n" +
            		"Agent: " + agent_name + "		" +
            		"Customer name: " + customer_name + "		" +
            		"Insurance type: " + insurance_type + "		" +
    				"Comments: " + comments + "\n");  
            pw.flush();
        }
        catch (IOException io) 
        {
        	io.printStackTrace();
        }
        finally 
        {
            try 
            {
                pw.close();
                bw.close();
                fw.close();
            } 
            catch (IOException io) 
            {
            	io.printStackTrace();
            }
        }
	}
	
	// Saves the details of a new sale to "db.csv"
	public void saveSaleToDBFile(String agent_name, String insurance_type, String due_date, 
										 String customer_first_name, String customer_last_name)
	{
		FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("db.csv", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.write(agent_name + "," + insurance_type + "," + due_date + ","
            		+ customer_first_name + "," + customer_last_name + "\n");  
            pw.flush();
        }
        catch (IOException io) 
        {
        	io.printStackTrace();
        }
        finally 
        {
            try 
            {
                pw.close();
                bw.close();
                fw.close();
            }
            catch (IOException io) 
            {
            	io.printStackTrace();
            }
        }
	}
	
	
	// Reads the customers names from "db.csv"
	public ArrayList<Customer> readCustomersFromDBFile()
	{
        ArrayList<Customer> customers = new ArrayList<Customer>();

		Path pathToFile = Paths.get("db.csv");
		try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)){
		
	        // read the first line from the text file
	        String line = br.readLine();
	
	        // loop until all lines are read
	        while (line != null) {
	
	            // use string.split to load a string array with the values from
	            // each line of the file, using a comma as the delimiter
	            String[] data = line.split(",");
	
	            Customer customer = new Customer(data[3], data[4]);
	
	            // adding book into ArrayList
	            customers.add(customer);
	
	            // read next line before looping
	            // if end of file reached, line would be null
	            line = br.readLine();
	        }
	    } 
		catch (IOException ioe) 
		{
	        ioe.printStackTrace();
	    }
		return customers;
	}
	
	// Checks if a tuple of sale with given details appears in the 'db.csv' file
	public boolean checkIfSaleExists(String agent_name, 
									 String insurance_type, 
									 String customer_name)
	{
		
		Path pathToFile = Paths.get("db.csv");
		try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)){
		
	        // read the first line from the text file
	        String line = br.readLine();
	
	        // loop until all lines are read
	        while (line != null) {
	
	        	// use string.split to load a string array with the values from
	            // each line of the file, using a comma as the delimiter
	            String[] data = line.split(",");
	            
	        	// Compare the saved sales to the given details
	        	// If found a match return true
	            if(data[0].equals(agent_name) &&
	               data[1].equals(insurance_type) &&
	               (data[3] + " " + data[4]).equals(customer_name))
	            	return true;
	
	            // read next line before looping
	            // if end of file reached, line would be null
	            line = br.readLine();
	        }
	        
	    } 
		catch (IOException ioe) 
		{
	        ioe.printStackTrace();
	    }
		
    	// No matching sale was found in the 'db.csv' file - return false
		return false;
	}
	
}
