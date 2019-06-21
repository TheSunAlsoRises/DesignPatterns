package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
        // initialization here, if needed...
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        // I really don't recommend using a single handler like this,
        // but it will work
        if (event.getSource() == cancelBtn) {
        	try {
				Main.replaceSceneContent("Main.fxml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	} else if (event.getSource() == claimBtn) {
            // do open action...
       	}
    }
}