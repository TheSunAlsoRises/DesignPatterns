/* 
 Class 'InsuranceTypes'
 Design Patterns: Factory, MVC - "Model View-Controller" version
 Role: Implements 'SettingsData'
  	 One of two types of data that are read from the "settings.xml" file 
		 (other is agents names).
		 Also acts as Model class to hold data about the insurance types.
*/

package application;

import java.util.ArrayList;

public class InsuranceTypes implements SettingsData{

	ArrayList<String> dataList = new ArrayList<String>();

	InsuranceTypes(ArrayList<String> dataFromFile){
		dataList = dataFromFile;
	}
	
	@Override
	public ArrayList<String> getDataList() {
		return dataList;
	}

}