/* 
 Class 'Agents'
 Design Patterns: Factory, MVC - "Model View-Controller" version
 Role: Implements 'SettingsData'
 		 One of two types of data that are read from the "settings.xml" file 
		(other is insurance types).
		 Also acts as Model class to hold data about the agents.
*/

package application;

import java.util.ArrayList;

public class Agents implements SettingsData{

	ArrayList<String> dataList = new ArrayList<String>();

	Agents(ArrayList<String> dataFromFile){
		dataList = dataFromFile;
	}
	
	@Override
	public ArrayList<String> getDataList() {
		return dataList;
	}

}
