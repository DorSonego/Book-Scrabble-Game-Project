package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.logic.host.GameManager;

public class ViewModel{ // Later implement ViewModelFacade
    //Properties for view:...
    public StringProperty wordFromPlayer;
    public StringProperty playerAction;

    //Other parameters:...
    GameManager model; //Game status



    public ViewModel(){
        wordFromPlayer = new SimpleStringProperty();
        playerAction = new SimpleStringProperty();
        model = null;
    }

    public void setModel(GameManager model) {
        this.model = model;
    }

    public GameManager getModel() {
        return model;
    }
}