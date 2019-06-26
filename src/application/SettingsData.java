// 
// Interface 'SettingsData'
// Design Pattern: Factory
// Role: Interface for 'SettingsData' data types
//  	 The basis of two types of data that are read from the "settings.xml" file (other is agents names)
//

package application;

import java.util.ArrayList;

public interface SettingsData {
	
	public ArrayList<String> getDataList();
		
}
