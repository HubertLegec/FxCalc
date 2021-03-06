package com.legec.fxCalc;

import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FxCalc extends Application {
    @FXML
    private GridPane rootGrid;
    @FXML
    private TextField resultField;

    private DoubleProperty fontSizeTracking = new SimpleDoubleProperty(Font.getDefault().getSize());
    private CalcController calcController = new CalcController();


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("fxcalc.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("FxCalc");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(525);
        primaryStage.setMaxWidth(2000);
        primaryStage.setMaxHeight(2000);
        primaryStage.show();
        initLayout();
    }

    private void initLayout() {
        ReadOnlyDoubleProperty heightProperty = rootGrid.heightProperty();
        ReadOnlyDoubleProperty widthProperty = rootGrid.widthProperty();

        fontSizeTracking.bind(
                new When(heightProperty.greaterThan(widthProperty))
                        .then(widthProperty.divide(12))
                        .otherwise(heightProperty.divide(12))
        );
        fontSizeTracking.addListener((observable, oldValue, newValue) ->
                rootGrid.setStyle("-fx-font-size: " + newValue.intValue())
        );

        resultField.textProperty()
                .bind(calcController.getDisplayStateProperty());
    }

    @FXML
    private void onClick(ActionEvent e) {
        Button b = (Button) e.getSource();
        calcController.processAction(b.getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
