package application;
	

import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calling Numbers From JavaFX Application");

        // Create the  grid pane
        GridPane gridPane = createPane();
        // Add UI controls to the  grid pane
        addUIControls(gridPane);
        // Create a scene with grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Phone call automation");
        headerLabel.setTextFill(Color.DEEPSKYBLUE);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        // Add Name Label
        Label numberLabel = new Label("Phone Number: ");
        numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(numberLabel, 0,1);

        // Add Name Text Field
        TextField numberField = new TextField();
        numberField.setPrefHeight(40);
        gridPane.add(numberField, 1,1);

         
        // Add Submit Button
        Button callButton = new Button("Call");
        callButton.setPrefHeight(40);
        callButton.setDefaultButton(true);
        callButton.setPrefWidth(100);
        gridPane.add(callButton, 0, 4, 2, 1);
        GridPane.setHalignment(callButton, HPos.CENTER);
        GridPane.setMargin(callButton, new Insets(1, 0,20,0));
     // Add text Button
        Button textButton = new Button("Text");
        textButton.setPrefHeight(40);
        textButton.setDefaultButton(true);
        textButton.setPrefWidth(100);
        gridPane.add(textButton, 0, 4, 2, 1);
        GridPane.setHalignment(textButton, HPos.LEFT);
        textButton.setTranslateX(200); 
        GridPane.setMargin(textButton, new Insets(1, 0,20,0));
     // Add call Button
        Button quitButton = new Button("Quit");
        quitButton.setPrefHeight(40);
        quitButton.setDefaultButton(true);
        quitButton.setPrefWidth(100);
        gridPane.add(quitButton, 0, 4, 2, 1);
        GridPane.setHalignment(quitButton, HPos.RIGHT);
        quitButton.setTranslateX(-200); 
        GridPane.setMargin(quitButton, new Insets(1, 0,20,0));
        quitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Platform.exit();

				
			}});
        textButton.setOnAction(new EventHandler<ActionEvent>() {
   
			@Override
			
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
                if(numberField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter the number you wish to text");
                    return;}
                
		        TextInputDialog td = new TextInputDialog("text");
		        td.setHeaderText("Enter the message you wish to send");
		        Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
		        td.setHeight(Region.USE_PREF_SIZE);
		        td.show();
		        okButton.setOnAction(e -> {
		            SendingMsg sendMsg = new SendingMsg();
		            try {
						sendMsg.textMsg(numberField.getText(),"test");
					} catch (MalformedURLException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		     
				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Successful Operation!", "You have just sent " + numberField.getText()+ " a message !");
		        });}});
        callButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(numberField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter the number you wish to call");
                    return;
                }
                CallingNumber callNumber = new CallingNumber();
                try {
					callNumber.call(numberField.getText());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Successful Operation!", "You are calling " + numberField.getText()+" NOW !");
            }
        });
    }
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}