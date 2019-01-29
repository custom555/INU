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
import toolkit.*;

public class PopupWindow {

	
	private static Stage popupStage;
	private static HBox pButtonBox;
	private static Image image;
	static MessageBoxResult result;
	
	private static Button abortBtn;
	private static Button retryBtn;
	private static Button ignoreBtn;
	private static Button okBtn;
	private static Button okPlainBtn;
	private static Button yesBtn;
	private static Button noBtn;
	private static Button cancelBtn;
	
	public static MessageBoxResult show(String title, String message, MessageBoxIcon icon, MessageBoxButtons buttons) {

		Text labelTxt = new Text();
		labelTxt.setText(message);
		labelTxt.setWrappingWidth(575);;
		
		setLabelFont(icon,labelTxt);	
		
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
		result = MessageBoxResult.getResult("");
		popupStage.showAndWait();
		
		return result;
	}

	private static void getIcon(MessageBoxIcon icon) {
		switch (icon) {
		case INFORMATION:
			image = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.INFORMATION.toString()));
			break;
		case WARNING:
			image = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.WARNING.toString()));
			break;
		case ALERT:
			image = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.ALERT.toString()));
			break;
		case CRITICAL_ERROR:
			image = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.CRITICAL_ERROR.toString()));
			break;
		case CONFIRM:
			image = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.CONFIRM.toString()));
			break;
		}
	}
	private static void setLabelFont(MessageBoxIcon messageType, Text message) {
		switch (messageType) {
		case INFORMATION:
			message.setFont(Font.font("Arial",FontWeight.NORMAL,16));
			break;
		case WARNING:
			message.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
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

	private static void getButtons(MessageBoxButtons buttons) {
		
		switch(buttons) {
			case ABORT_RETRY_IGNORE:
				abortBtn = new Button(buttons.getText(0));
				retryBtn = new Button(buttons.getText(1));
				ignoreBtn = new Button(buttons.getText(2));
				addAndSetButton(abortBtn,retryBtn,ignoreBtn);
				break;
			case OK:
				okBtn = new Button(buttons.getText(0));
				addAndSetButton(okBtn);
				break;
			case OK_CANCEL:
				okPlainBtn = new Button(buttons.getText(0));
				cancelBtn = new Button(buttons.getText(1));
				addAndSetButton(okPlainBtn,cancelBtn);
				break;
			case YES_NO:
				yesBtn = new Button(buttons.getText(0));
				noBtn = new Button(buttons.getText(1));
				addAndSetButton(yesBtn,noBtn);
				break;
			case RETRY_CANCEL:
				retryBtn = new Button(buttons.getText(0));
				cancelBtn = new Button(buttons.getText(1));
				addAndSetButton(retryBtn,cancelBtn);
				break;
		}
	}
	private static void setButtonListener(Button b) {
		b.setOnAction(e -> {result = MessageBoxResult.getResult(b.getText()); popupStage.close();});
	}
	private static void adjustButtonSize(Button b) {
		b.setPrefSize(120, 40);
	}
	private static void setButtonIcon(Button b) {
		Image yesImage = new Image(ClassLoader.getSystemResourceAsStream("StatusOK_32x.png"));
		Image noImage = new Image(ClassLoader.getSystemResourceAsStream("StatusNo_32xLG.png"));
		
		if(b.equals(okBtn)) {
			okBtn.setGraphic(new ImageView(yesImage));
			okBtn.setGraphicTextGap(10);
		}
		else if(b.equals(yesBtn)) {
			yesBtn.setGraphic(new ImageView(yesImage));
			yesBtn.setGraphicTextGap(10);
		}
		else if(b.equals(noBtn)) {
			noBtn.setGraphic(new ImageView(noImage));
			noBtn.setGraphicTextGap(10);
		}
	}
	private static void adjustButtonDefaultOperation(Button b){
		if(b.equals(okBtn)) {
			okBtn.setDefaultButton(true);
		}
		else if(b.equals(yesBtn)) {
			yesBtn.setDefaultButton(true);
		}
		else if(b.equals(cancelBtn)) {
			cancelBtn.setCancelButton(true);
		}	
	}
	private static void addAndSetButton(Button... b) {
		for(int i=0; i<b.length; i++) {
			setButtonIcon(b[i]);
			setButtonListener(b[i]);
			adjustButtonDefaultOperation(b[i]);
			adjustButtonSize(b[i]);
		}
		pButtonBox.getChildren().addAll(b);
	}
}

