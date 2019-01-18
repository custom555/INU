package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PopupWindow {

	public enum MessageBoxIcon {INFORMATION, WARNING, ALERT, CRITICAL_ERROR, CONFIRM};
	public enum MessageBoxButton {ABORT_RETRY_IGNORE, OK, OK_CANCEL, RETRY_CANCEL, YES_NO};
	public enum MessageBoxResult{ABORT, RETRY, IGNORE, OK, CANCEL, YES, NO};
	public static MessageBoxResult result = MessageBoxResult.CANCEL;
	
	private static Stage popupStage;
	private static HBox pButtonBox;
	private static Image image;
	
	private static Button abortBtn = new Button("Przerwij");
	private static Button retryBtn = new Button("Ponów");
	private static Button ignoreBtn = new Button("Ignoruj");
	private static Button okBtn = new Button("Ok");
	private static Button yesBtn = new Button("Tak");
	private static Button noBtn = new Button("Nie");
	private static Button cancelBtn = new Button("Anuluj");
	
	public static MessageBoxResult show(String title, String message, MessageBoxIcon icon, MessageBoxButton buttons) {
		
		Text labelTxt = new Text();
		labelTxt.setText(message);
		labelTxt.setWrappingWidth(575);;
		
		setLabelFont(icon,labelTxt);	
		adjustButtonsSize();
		adjustButtonsDefaultOperations();
		setButtonsListeners();
		setButtonsIcons();
		
		popupStage = new Stage();
		popupStage.setTitle(title);
		popupStage.initModality(Modality.APPLICATION_MODAL);
			
		VBox pRoot = new VBox();
		pRoot.setPadding(new Insets(25));
		pRoot.setSpacing(25);
		
		HBox pTextBox = new HBox(labelTxt);
		pTextBox.setPrefHeight(120);
		
		pButtonBox = new HBox(30);
		pButtonBox.setAlignment(Pos.CENTER);
		
		getButtons(buttons);
		getIcon(icon);
		
		HBox topSide = new HBox(25,new ImageView(image),pTextBox);
		topSide.setAlignment(Pos.CENTER_LEFT);
		
		HBox downSide = new HBox(pButtonBox);
		downSide.setAlignment(Pos.CENTER);
				
		pRoot.getChildren().addAll(topSide,downSide);
		Scene pScene = new Scene(pRoot,700,180);
		
		popupStage.setScene(pScene);
		popupStage.setResizable(false);
		popupStage.showAndWait();
		
		return result;
	}

	private static void getIcon(MessageBoxIcon icon) {
		switch (icon) {
		case INFORMATION:
			image = new Image(ClassLoader.getSystemResourceAsStream("StatusInformation_64x.png"));
			break;
		case WARNING:
			image = new Image(ClassLoader.getSystemResourceAsStream("StatusWarning_64x.png"));
			break;
		case ALERT:
			image = new Image(ClassLoader.getSystemResourceAsStream("StatusAlert_64x.png"));
			break;
		case CRITICAL_ERROR:
			image = new Image(ClassLoader.getSystemResourceAsStream("StatusCriticalError_64x.png"));
			break;
		case CONFIRM:
			image = new Image(ClassLoader.getSystemResourceAsStream("StatusHelp_64x.png"));
			break;
		}
	}
	private static void setLabelFont(MessageBoxIcon messageType, Text message) {
		switch (messageType) {
		case INFORMATION:
			message.setFont(Font.font("Arial",FontWeight.NORMAL,19));
			break;
		case WARNING:
			message.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
			break;
		case ALERT:
			message.setFont(Font.font("Tahoma",FontWeight.LIGHT,17));
			break;
		case CRITICAL_ERROR:
			message.setFont(Font.font("Courier New",FontWeight.THIN,16));
			break;
		case CONFIRM:
			message.setFont(Font.font("Helvetica",FontPosture.ITALIC,16));
			break;
		}
	}

	private static void getButtons(MessageBoxButton buttons) {
		
		switch(buttons) {
			case ABORT_RETRY_IGNORE:
				pButtonBox.getChildren().addAll(abortBtn,retryBtn,ignoreBtn);
				break;
			case OK:
				pButtonBox.getChildren().addAll(okBtn);
				break;
			case OK_CANCEL:
				pButtonBox.getChildren().addAll(okBtn, cancelBtn);
				break;
			case YES_NO:
				pButtonBox.getChildren().addAll(yesBtn,noBtn);
				break;
			case RETRY_CANCEL:
				pButtonBox.getChildren().addAll(retryBtn,cancelBtn);
				break;
		}
	}
	private static void setButtonsListeners() {
		abortBtn.setOnAction(e -> {result = MessageBoxResult.ABORT; popupStage.close();});
		retryBtn.setOnAction(e -> {result = MessageBoxResult.RETRY; popupStage.close();});
		ignoreBtn.setOnAction(e -> {result = MessageBoxResult.IGNORE; popupStage.close();});
		okBtn.setOnAction(e -> {result = MessageBoxResult.OK; popupStage.close();});
		yesBtn.setOnAction(e -> {result = MessageBoxResult.YES; popupStage.close();});
		noBtn.setOnAction(e -> {result = MessageBoxResult.NO; popupStage.close();});
		cancelBtn.setOnAction(e -> {result = MessageBoxResult.CANCEL; popupStage.close();});
	}
	private static void adjustButtonsSize() {
		abortBtn.setPrefSize(120, 40);
		retryBtn.setPrefSize(120, 40);
		ignoreBtn.setPrefSize(120, 40);
		okBtn.setPrefSize(120, 40);
		cancelBtn.setPrefSize(120, 40);
		okBtn.setPrefSize(120, 40);
		yesBtn.setPrefSize(120, 40);
		noBtn.setPrefSize(120, 40);
		cancelBtn.setPrefSize(120, 40);
	}
	private static void setButtonsIcons() {
		Image yesImage = new Image(ClassLoader.getSystemResourceAsStream("StatusOK_32x.png"));
		Image noImage = new Image(ClassLoader.getSystemResourceAsStream("StatusNo_32xLG.png"));
		
		okBtn.setGraphic(new ImageView(yesImage));
		okBtn.setGraphicTextGap(10);
		yesBtn.setGraphic(new ImageView(yesImage));
		yesBtn.setGraphicTextGap(10);
		noBtn.setGraphic(new ImageView(noImage));
		noBtn.setGraphicTextGap(10);
	}
	private static void adjustButtonsDefaultOperations(){
		okBtn.setDefaultButton(true);
		yesBtn.setDefaultButton(true);
		cancelBtn.setCancelButton(true);
	}
}

