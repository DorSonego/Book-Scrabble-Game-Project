package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;

public class BoardDisplayer extends Canvas {
    int[][] BoardData;
    public StringProperty boardFileName;
    public BoardDisplayer() {
        boardFileName= new SimpleStringProperty();
    }
    public void setBoardFileName(String fileName) {
        this.boardFileName.set(fileName);
    }

    public String getBoardFileName() {
        return boardFileName.get();
    }




}
