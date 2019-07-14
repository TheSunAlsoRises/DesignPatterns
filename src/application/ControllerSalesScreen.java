/* 
 Class 'ControllerSalesScreen'
 Design Pattern: MVC - "Model View-Controller" version
 Role: Combined View-Controller class to view the sales screen and control its background.
		 Uses the Factory to get data from the Model classes 'Agents' and 'InsuranceTypes'
		 The singelton class 'LogWriter' extends this controller in matters of I/O.

*/

package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerSalesScreen {

    @FXML
    private ComboBox<String> agent;
    
    @FXML
    private ComboBox<String> insuranceType;    
    
    @FXML
    private TextField dueDate;
    
    @FXML
    private TextField custFirstName;
    
    @FXML
    private TextField custLastName;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    public void initialize() {
    	
        // Initialize 'agent' combo boxes
    	ArrayList<String> list_agents = SettingsDataFactory.getSettingsData("agents").getDataList();
        ObservableList obArrList_agents = FXCollections.observableArrayList(list_agents);
        agent.getItems().clear();
        agent.setItems(obArrList_agents);
        
        // Initialize 'insuranceType' combo boxes
        ArrayList<String> list_instypes = SettingsDataFactory.getSettingsData("types").getDataList();
        ObservableList obArrList_instypes = FXCollections.observableArrayList(list_instypes);
        insuranceType.getItems().clear();
        insuranceType.setItems(obArrList_instypes);
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
    	// Go back to menu
        if (event.getSource() == cancelBtn) {
        	try {
				Main.replaceSceneContent("Main.fxml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	}
    	// Attempting to save
        else if (event.getSource() == saveBtn) {
        	
        	// Check if all the required field were filled
        	String a = custLastName.getText();
        	if(agent.getValue() != null && insuranceType.getValue() != null && 
        		dueDate.getText() != "" && !custFirstName.getText().equals("") && 
        		!custLastName.getText().equals("")){
        		
	       		Alert info = new Alert(AlertType.INFORMATION, "Data was saved", ButtonType.OK);
	       		info.showAndWait();
	
	       		if (info.getResult() == ButtonType.OK) {
	       			// Print to logger.txt
	       			LogWriter logwriter = LogWriter.getInstance();
	       			logwriter.saveSaleToLoggerFile(agent.getValue(), 
	       					insuranceType.getValue(), dueDate.getText(),  
	       					custFirstName.getText(), custLastName.getText());
	       			
	       			// Then go back to menu
	       			try {
	    				Main.replaceSceneContent("Main.fxml");
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	       		}
        	}
        	
       		// Not all the required field were filled
       		else{
	       		Alert alert = new Alert(AlertType.ERROR, "Please fill the fields", ButtonType.OK);
	       		alert.showAndWait();
	
	       		if (alert.getResult() == ButtonType.OK) {
	       		    //do stuff
	       		}
       		}
       	}
    }
}