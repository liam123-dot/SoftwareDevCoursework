public class GameState {


    private Boolean gameWon = false;
    private Player winner;

    public GameState() {
    }

    public synchronized void setGameWon(Player winner) {
        this.gameWon = true;
        this.winner = winner;
    }

    public synchronized Boolean getGameWon() {
        return gameWon;
    }

    public synchronized Player getWinner() {
        return winner;
    }
    
}
