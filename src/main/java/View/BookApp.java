package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.data.ViewSharedData;
import viewModel.ViewModel;

import java.io.IOException;
import java.util.Objects;

public class BookScrabbleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        ViewModel viewModel = new ViewModel();
        ViewSharedData sharedData = new ViewSharedData(viewModel);

        ViewController viewController = loader.getController();
        viewController.setViewSharedData(sharedData);

        Scene scene = new Scene(root, 1000, 550);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("HomePage.css")).toExternalForm());

        stage.setUserData(viewModel);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println("main is dead");
    }
}