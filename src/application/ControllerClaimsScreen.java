/* 
 Class 'ControllerClaimsScreen'
 Design Pattern: MVC - "Model View-Controller" version
 Role: Combined View-Controller class to view the claims screen and control its background.
		 Uses the Factory to get data in the form of Model classes 'Agents' and 'InsuranceTypes'.
		 Also uses the class 'LogWriter' to get data in the form of Model class 'Customer'.
		 The singelton class 'LogWriter' extends this controller in matters of I/O.
*/

package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerClaimsScreen {

    @FXML
    private ComboBox<String> agent;
    
    @FXML
    private ComboBox<String> customerName;    
    
    @FXML
    private ComboBox<String> insuranceType;    
    
    @FXML
    private TextField comments;
    
    @FXML
    private Button claimBtn;
    
    @FXML
    private Button cancelBtn;
    
    public void initialize() {
    	
        // Initialize 'agent' combo box using the Factory & 'Agents' Model class
    	ArrayList<String> list_agents = SettingsDataFactory.getSettingsData("agents").getDataList();
        ObservableList obArrList_agents = FXCollections.observableArrayList(list_agents);
        agent.getItems().clear();
        agent.setItems(obArrList_agents);
        
        // Initialize 'customerName' combo box using Singelton class 'LogWriter' & 
        //'Customer' Model class - Read from 'db.csv'
        LogWriter logwriter = LogWriter.getInstance();
        ArrayList<String> list_customersNames = Customer.customersArrayListToStrings(logwriter.readCustomersFromDBFile());
        ObservableList obArrList_customersNames = FXCollections.observableArrayList(list_customersNames);
        customerName.getItems().clear();
        customerName.setItems(obArrList_customersNames);
        
        // Initialize 'insuranceType' combo box using the Factory & 'InsuranceTypes' Model class
        ArrayList<String> list_insuranceType = SettingsDataFactory.getSettingsData("types").getDataList();
        ObservableList obArrList_insuranceType = FXCollections.observableArrayList(list_insuranceType);
        insuranceType.getItems().clear();
        insuranceType.setItems(obArrList_insuranceType);
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {

        if (event.getSource() == cancelBtn) {
        	try {
				Main.replaceSceneContent("Main.fxml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	} 
     // Attempting to save
        else if (event.getSource() == claimBtn) {
        	
        	// Check if all the required field were filled
        	if(agent.getValue() != null && customerName.getValue() != null && 
        		insuranceType.getValue() != null){
        		
        		// Check if the claim matches a sale in the 'db.csv' file
        		// Can't confirm a claim if a matching insurance was'nt sold
       			LogWriter logwriter = LogWriter.getInstance();
        		if(true == logwriter.checkIfSaleExists(agent.getValue().toString(), 
        											   insuranceType.getValue().toString(), 
        											   customerName.getValue().toString())){
        		
		       		Alert info = new Alert(AlertType.INFORMATION, "Data was saved", ButtonType.OK);
		       		info.showAndWait();
		
		       		if (info.getResult() == ButtonType.OK) {
		       			// Print to logger.txt
		       			logwriter = LogWriter.getInstance();
		       			logwriter.saveClaimToLoggerFile(agent.getValue(), 
		       					customerName.getValue(), insuranceType.getValue(),
		       					comments.getText());
		       			
		       			// Then go back to menu
		       			try {
		    				Main.replaceSceneContent("Main.fxml");
		    			} catch (Exception e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		       		}
	        	}
        		// Could not find matching sale
        		else 
        		{
        			Alert alert = new Alert(AlertType.ERROR, "The details does not match an exsiting sale", ButtonType.OK);
    	       		alert.showAndWait();
    	
    	       		if (alert.getResult() == ButtonType.OK) {
    	       		    // Do nothing, stay in screen
    	       		}
        		}
        	}
       		// Not all the required field were filled
       		else{
	       		Alert alert = new Alert(AlertType.ERROR, "Please fill the required fields", ButtonType.OK);
	       		alert.showAndWait();
	
	       		if (alert.getResult() == ButtonType.OK) {
	       		    // Do nothing, stay in screen
	       		}
       		}
       	}
    }
}