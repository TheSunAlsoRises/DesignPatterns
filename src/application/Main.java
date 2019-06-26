// 
// Class 'ControllerClaimsScreen'
// Design Pattern: MVC - "Model View-Controller" version
// Role: Combined View-Controller class to view the main screen and control its background.
//		 Does not interact with Model data
//

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static BorderPane root;
	private static Stage stage;
	private static Scene scene;
	@Override
	public void start(Stage primaryStage) {
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			scene = new Scene(root,300,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			stage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void replaceSceneContent(String fxml){
		try {
			root = (BorderPane)FXMLLoader.load(Main.class.getResource(fxml));
			if (fxml != "Main.fxml"){
				scene = new Scene(root,330,400);
			}else {
				scene = new Scene(root,300,300);
			}
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle(fxml.substring(0,fxml.length()-5));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
