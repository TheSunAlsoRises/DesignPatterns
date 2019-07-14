/* 
 Class 'SettingsDataFactory'
 Design Pattern: Factory
 Role: Factory of 'SettingsData'
  	 Creates two types of data that are read from the "settings.xml" file (other is agents names)
*/

package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SettingsDataFactory {

	public static SettingsData getSettingsData(String identifier){
		
		switch(identifier){
			case "agents":
				// Get agents from file 
				ArrayList<String> agents_names = parseXml("Agent");
				// Send to constructor
				Agents agents = new Agents(agents_names);
				return agents;
			
			case "types":
				// Get types from file 
				ArrayList<String> types_names = parseXml("Type");
				// Send to constructor
				InsuranceTypes types = new InsuranceTypes(types_names);
				return types;		
				
			default:		
				return null;
		}
	}

	private static ArrayList<String> parseXml(String tag){
		
		ArrayList<String> data = new ArrayList<String>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse("settings.xml");
			NodeList options = doc.getElementsByTagName(tag);
			for(int i=0;i<options.getLength();i++){
				data.add(options.item(i).getTextContent());
			}
		}
		catch(ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch(SAXException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}	
}

