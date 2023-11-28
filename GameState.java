public class GameState {


    private Boolean gameWon = false;
    private Player winner;
    private CardDeck[] decks;

    public GameState(CardDeck[] decks) {
        this.decks = decks;
    }

    public synchronized void setGameWon(Player winner) {
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
        for (CardDeck deck : decks) {
            deck.outputFinalDeck();
        }
    }
    
}
