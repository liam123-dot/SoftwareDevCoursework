public class GameState {
    //GameState class


    private Boolean gameWon = false;
    private Player winner;
    private CardDeck[] decks;

    public GameState(CardDeck[] decks) {
        this.decks = decks;
    }

    public synchronized void setGameWon(Player winner) {
    // Sets the game as won by a specific player and updates the winner and decks accordingly.
        if (this.gameWon) {
            return;
        }
        this.gameWon = true;
        this.winner = winner;
        outputDecks();
    }

    public synchronized Boolean getGameWon() {
        return gameWon;
    }

    public synchronized Player getWinner() {
        return winner;
    }
    
    private void outputDecks() {
    // Outputs the final contents of all card decks
        for (CardDeck deck : decks) {
            deck.outputFinalDeck();
        }
    }
    
}
