package com.legec.fxCalc;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FxCalc extends Application {
    @FXML
    protected TextField resultField;
    @FXML
    protected Button resultBT;
    @FXML
    protected Button percentBT;
    @FXML
    protected Button sqrtButton;
    @FXML
    protected Button clearButton;
    @FXML
    protected Button plusBT;
    @FXML
    protected Button minusBT;
    @FXML
    protected Button mulBT;
    @FXML
    protected Button divBT;
    @FXML
    protected Button signBT;
    @FXML
    protected Button dotBT;
    @FXML
    protected Button zeroBT;
    @FXML
    protected Button oneBT;
    @FXML
    protected Button twoBT;
    @FXML
    protected Button threeBT;
    @FXML
    protected Button fourBT;
    @FXML
    protected Button fiveBT;
    @FXML
    protected Button sixBT;
    @FXML
    protected Button sevenBT;
    @FXML
    protected Button eightBT;
    @FXML
    protected Button nineBT;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxcalc.fxml"));
        primaryStage.setTitle("FxCalc");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
