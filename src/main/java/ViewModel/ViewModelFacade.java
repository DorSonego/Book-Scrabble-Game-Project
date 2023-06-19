package viewModel;

public interface ViewModelFacade {

    public void firstConnection(String ip, int port, String playerName);
    public void updateView(); //Uses GameManager(Model) for view data.
    public void updateGameManager(); //Updates current model status.

    //Methods for updateView:
    public void updateScore();
    public void updateConnectedPlayers();
    public void updatePlayerTiles();
    public void updateBoard();
    public void toggleView(); // Used to disable and enable buttons in view(depending on player turn).

    //Game functionality:
    public void playerAction(); // Switch case for all functions/buttons player can use.

    //All functions playerAction can use:
    public void submit(); // Uses StringProperty wordFromPlayer to update model for player submission.
    public void skipTurn();
    public void resign(); // Linked to Exit button
    public void sortTiles();
    public void swapTiles();
    public void challenge();

}