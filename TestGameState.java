import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameState{

    @Test
    public void setGameWon_setsWinnerAndGameWon() {
        CardDeck[] decks = new CardDeck[1];
        decks[0] = new CardDeck(1);
        GameState gameState = new GameState(decks);
        Player player = new Player(1, null, null, null);

        gameState.setGameWon(player);

        assertTrue(gameState.getGameWon());
        assertEquals(player, gameState.getWinner());
    }

    @Test
    public void setGameWon_doesNotOverwriteExistingWinner() {
        CardDeck[] decks = new CardDeck[1];
        decks[0] = new CardDeck(1);
        GameState gameState = new GameState(decks);
        Player player1 = new Player(1, null, null, null);
        Player player2 = new Player(2, null, null, null);

        gameState.setGameWon(player1);
        gameState.setGameWon(player2);

        assertTrue(gameState.getGameWon());
        assertEquals(player1, gameState.getWinner());
    }

    @Test
    public void getGameWon_returnsFalseWhenGameNotWon() {
        CardDeck[] decks = new CardDeck[1];
        decks[0] = new CardDeck(1);
        GameState gameState = new GameState(decks);

        assertFalse(gameState.getGameWon());
    }

    @Test
    public void getWinner_returnsNullWhenNoWinner() {
        CardDeck[] decks = new CardDeck[1];
        decks[0] = new CardDeck(1);
        GameState gameState = new GameState(decks);

        assertNull(gameState.getWinner());
    }
}