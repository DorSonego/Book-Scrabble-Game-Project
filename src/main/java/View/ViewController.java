package view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import view.data.ViewSharedData;

import java.io.IOException;
import java.util.Objects;

public class ViewController {
    ViewSharedData viewSharedData;
    public StringProperty wordFromPlayer;
    public StringProperty playerAction;

    public ViewController(){
        wordFromPlayer = new SimpleStringProperty();
        playerAction = new SimpleStringProperty();
    }

    public void setViewSharedData(ViewSharedData viewSharedData) {
        this.viewSharedData = viewSharedData;
    }


    @FXML
    public void loadScene(ActionEvent event, String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(sceneName + ".fxml")));
        Parent root = loader.load();
        ViewController viewController = null;
        ConnectionController connectionController = null;
        switch(sceneName){
            case "GameModePage" -> connectionController = loader.getController();
            case "Tutorial", "HomePage" -> viewController = loader.getController();
        }
        if(viewController != null) viewController.setViewSharedData(this.viewSharedData);
        else if(connectionController != null) connectionController.setViewSharedData(this.viewSharedData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = null;
        if(Objects.equals(sceneName, "BoardPage")){
            scene = new Scene(root,1400,1000);
        } else{
            scene = new Scene(root);
        }
        try {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(sceneName + ".css")).toExternalForm());
        } catch (NullPointerException ignored){}
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void StartTutorial(ActionEvent event) throws IOException{
        loadScene(event, "Tutorial");
    }

    @FXML
    public void ChooseGameMode(ActionEvent event) throws IOException{
        loadScene(event, "GameModePage");
    }

    @FXML
    public void loadHomePage(ActionEvent event) throws IOException{
        loadScene(event, "HomePage");
    }


    @FXML
    public void loadBoard(ActionEvent event) throws IOException{
        loadScene(event, "BoardPage");
    }

    @FXML
    public void Exit(ActionEvent event) throws IOException{
        Platform.exit();
    }

    @FXML
    public void Back(ActionEvent event) throws IOException{
        ChooseGameMode(event);
    }

}