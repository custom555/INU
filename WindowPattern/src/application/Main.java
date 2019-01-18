package application;

import application.PopupWindow.MessageBoxButton;
import application.PopupWindow.MessageBoxIcon;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam arcu nibh, tincidunt quis mollis non,"
			+ " mattis nec sem. Proin mollis nisl nec nunc ultricies placerat. Ut id accumsan neque. Ut vitae neque eget tortor"
			+ " finibus laoreet.";
	@Override
	public void start(Stage primaryStage) {
		try {
			Button informationBtn = new Button("Informacja");
			Button errorBtn = new Button("B³¹d");
			Button warningBtn = new Button("Ostrze¿enie");
			Button alertBtn = new Button("Alert");
			Button confirmBtn  = new Button("Potwierdzenie");
			Label label = new Label();
			
	
			informationBtn.setOnAction(e -> {
				PopupWindow.show("Okienko informacyjne",loremIpsum,MessageBoxIcon.INFORMATION,MessageBoxButton.OK);
				label.setText(PopupWindow.result.toString());
			});
			errorBtn.setOnAction(e -> {
				PopupWindow.show("Okienko B³êdu",loremIpsum,MessageBoxIcon.CRITICAL_ERROR,MessageBoxButton.ABORT_RETRY_IGNORE);
				label.setText(PopupWindow.result.toString());
			});
			warningBtn.setOnAction(e -> {
				PopupWindow.show("Okienko Ostrze¿enia",loremIpsum,MessageBoxIcon.WARNING,MessageBoxButton.OK_CANCEL);
				label.setText(PopupWindow.result.toString());
			});
			alertBtn.setOnAction(e -> {
				PopupWindow.show("Okienko Alert",loremIpsum,MessageBoxIcon.ALERT,MessageBoxButton.RETRY_CANCEL);
				label.setText(PopupWindow.result.toString());
			});
			confirmBtn.setOnAction(e -> {
				PopupWindow.show("Okienko Potwierdzenia",loremIpsum,MessageBoxIcon.CONFIRM,MessageBoxButton.YES_NO);
				label.setText(PopupWindow.result.toString());
			});
			
			informationBtn.setPrefWidth(120);
			errorBtn.setPrefWidth(120);
			alertBtn.setPrefWidth(120);
			warningBtn.setPrefWidth(120);
			confirmBtn.setPrefWidth(120);
			
			HBox buttonBox = new HBox(informationBtn,errorBtn,alertBtn,warningBtn,confirmBtn);
			buttonBox.setPadding(new Insets(10));
			buttonBox.setSpacing(30);
			buttonBox.setAlignment(Pos.CENTER);
			
			HBox informationBox = new HBox(label);
			informationBox.setPadding(new Insets(10));
			informationBox.setSpacing(20);
			informationBox.setAlignment(Pos.CENTER);
			
			VBox root = new VBox();
			root.getChildren().addAll(buttonBox,informationBox);
			root.setPadding(new Insets(25));
			
			Scene scene = new Scene(root,800,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Window Pattern Test");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
