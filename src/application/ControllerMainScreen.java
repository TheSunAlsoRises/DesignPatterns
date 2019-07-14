/* 
 Class 'ControllerMainScreen'
 Design Pattern: MVC - "Model View-Controller" version
 Role: Combined View-Controller class to view the main screen and control its background.
*/

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class ControllerMainScreen {

    @FXML
    private Button salesBtn;

    @FXML
    private Button claimsBtn;
    
    @FXML
    private Button exitBtn;
    
    public void initialize() {
        // initialization here, if needed...
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        // I really don't recommend using a single handler like this,
        // but it will work
        if (event.getSource() == salesBtn) {
        	try {
				Main.replaceSceneContent("Sales.fxml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	} else if (event.getSource() == claimsBtn) {
            	try {
    				Main.replaceSceneContent("Claims.fxml");
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
       	}
    	else if (event.getSource() == exitBtn) {
        	Stage stage = (Stage)exitBtn.getScene().getWindow();
        	stage.close();
   		}
    }
}