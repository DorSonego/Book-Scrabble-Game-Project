package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.logic.host.MySocket;
import view.data.ViewSharedData;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class GameController {
    String word;
    boolean vertical;
    int flag;
    ArrayList<Integer> indexRow;
    ArrayList<Integer> indexCol;
    @FXML
    GridPane boardGridPane;
    ViewSharedData viewSharedData;

    public GameController(){
        this.word = "";
        this.vertical = false;
        this.flag = 0;
        this.indexRow = new ArrayList<>();
        this.indexCol = new ArrayList<>();
        //Binding..
    }

    public void setViewSharedData(ViewSharedData viewSharedData) {
        this.viewSharedData = viewSharedData;
    }

    @FXML
    public void Submit(ActionEvent event) throws IOException {
        while(this.flag == 0){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Choose Vertical or Horizontal");
            dialog.setHeaderText("Enter Vertical yes/no");
            dialog.setContentText("Vertical:");
            Optional<String> result = dialog.showAndWait();
            if(result.get().equals("yes")){
                this.vertical=true;
                this.flag=1;
                break;
            }else if (result.get().equals("no")){
                this.vertical=false;
                this.flag=0;
                break;
            }
        }
        int startRow =-1, startCol=-1;
        int endRow =-1, endCol=-1;
        if(this.vertical){
            startRow = this.indexRow.get(0);
            startCol = this.indexCol.get(0);
            endRow = this.indexRow.get(this.indexRow.size()-1);
            endCol = this.indexCol.get(this.indexCol.size()-1);
        }
        else{
            startRow = this.indexRow.get(0);
            startCol = this.indexCol.get(0);
            endRow = this.indexRow.get(this.indexRow.size()-1);
            endCol = this.indexCol.get(this.indexCol.size()-1);
        }
        System.out.println("Submit");
        System.out.println("Word: " + this.word + ", Vertical: " + this.vertical);
        System.out.println("Start at index: " + startRow + ", " + startCol);
        System.out.println("End at index: " + endRow + ", " + endCol);
        this.indexRow.clear();
        this.indexCol.clear();
        this.word = "";
        this.flag = 0;
    }
    @FXML
    public void Challenge(ActionEvent event) throws IOException{
        System.out.println("Challenge");
    }
    @FXML
    public void SwapTiles(ActionEvent event) throws IOException{
        System.out.println("SwapTiles");
    }
    @FXML
    public void SortTiles(ActionEvent event) throws IOException{
        System.out.println("Sort");
    }
    @FXML
    public void SkipTurn(ActionEvent event) throws IOException{
        System.out.println("SkipTurn");
    }
    @FXML
    public void Resign(ActionEvent event) throws IOException{
        System.out.println("Resign");
    }

    public void squareClickHandler() {
        // Run through all the children of boardGridPane
        for (Node node : boardGridPane.getChildren()) {
            if (node instanceof StackPane) {
                StackPane cell = (StackPane) node;
                Label label = (Label) cell.getChildren().get(0);
                ImageView imageView = new ImageView();

                // Add click event handler to each cell
                cell.setOnMouseClicked(event -> {
                    // Create a TextInputDialog for entering the letter and orientation
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Enter a Letter");
                    dialog.setHeaderText("Enter a letter for the cell");
                    dialog.setContentText("Letter:");
                    TextField orientationField = new TextField();

                    // Show the dialog and wait for the user's input
                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(word -> {
                        String letter = word.trim();
                        String orientation = orientationField.getText().trim().toLowerCase();
                        this.vertical = orientation.equals("yes");
                        // TODO - implement the vertical logic.
                        // Validate the entered letter
                        if (letter.length() != 1 || !Character.isLetter(letter.charAt(0))) {
                            new Alert(Alert.AlertType.ERROR, "Only one letter is allowed.").showAndWait();
                            return;
                        }

                        // Generate the image path based on the entered letter
                        String imagePath = "/Images/Tiles/" + letter.toUpperCase() + ".png";

                        // Set the background image and remove the background color
                        cell.setId("cell"); // Set an ID for the StackPane
                        cell.setStyle("-fx-background-color: transparent;"); // Remove the background color

                        // Update the label text
                        label.setText(letter.toUpperCase());
                        label.setVisible(false);

                        // Set the background image using JavaFX
                        String fullPath = Objects.requireNonNull(ViewController.class.getResource(imagePath)).toExternalForm();
                        imageView.setImage(new Image(fullPath));
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(cell.getWidth());
                        imageView.setFitHeight(cell.getHeight()+ 3);
                        cell.getChildren().add(imageView);

                        // Save the word, its orientation, and the index
                        int index = boardGridPane.getChildren().indexOf(cell);
                        saveWordWithOrientationAndIndex(letter, orientation, index);
                    });
                });
            }
        }
    }

    // Method to save the entered word, its orientation, and retrieve the column and row index
    private void saveWordWithOrientationAndIndex(String word, String orientation, int index) {
        // Retrieve the column and row index based on the StackPane index within the GridPane
        int numColumns = GridPane.getColumnIndex(boardGridPane.getChildren().get(index));
        int numRows = GridPane.getRowIndex(boardGridPane.getChildren().get(index));
        // Implement your saving logic here
        this.word += word;
        this.indexCol.add(numColumns);
        this.indexRow.add(numRows);
    }

    @FXML
    public void start(ActionEvent event) throws IOException{
        squareClickHandler();
        sendStartToServer();
    }

    //Testing ONLY
    public void sendStartToServer(){
        try {
            MySocket initiateServer = new MySocket(new Socket(viewSharedData.getHostIp(), viewSharedData.getHostPort()));
            PrintWriter printWriter = new PrintWriter(initiateServer.getPlayerSocket().getOutputStream(),true);
            printWriter.println("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Exit(ActionEvent event) throws IOException{
        Platform.exit();
    }
}