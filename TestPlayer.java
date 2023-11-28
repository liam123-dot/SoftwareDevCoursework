import org.junit.Test;
import static org.junit.Assert.*;


public class TestPlayer {

    @Test
    public void testCheckHasWon() {
        Card[] hand = new Card[4];
        hand[0] = new Card(1);
        hand[1] = new Card(1);
        hand[2] = new Card(1);
        hand[3] = new Card(1);

        CardDeck previousDeck = new CardDeck(1);
        CardDeck nextDeck = new CardDeck(2);

        GameState gameState = new GameState(new CardDeck[0]);

        Player player = new Player(1, previousDeck, nextDeck, gameState);
        player.setHand(hand);

        assertTrue(player.checkHasWon());
    }

    @Test
    public void testCheckHasNotWon() {
        Card[] hand = new Card[4];
        hand[0] = new Card(1);
        hand[1] = new Card(2);
        hand[2] = new Card(3);
        hand[3] = new Card(4);

        CardDeck previousDeck = new CardDeck(1);
        CardDeck nextDeck = new CardDeck(2);

        GameState gameState = new GameState(new CardDeck[0]);

        Player player = new Player(1, previousDeck, nextDeck, gameState);
        player.setHand(hand);

        assertFalse(player.checkHasWon());
    }

    @Test
    public void testGetNextIndex() {
        Card[] hand = new Card[4];
        hand[0] = new Card(1);
        hand[1] = new Card(2);
        hand[2] = new Card(3);
        hand[3] = new Card(4);

        CardDeck previousDeck = new CardDeck(1);
        CardDeck nextDeck = new CardDeck(2);

        GameState gameState = new GameState(new CardDeck[0]);

        Player player = new Player(1, previousDeck, nextDeck, gameState);
        player.setHand(hand);

        for (int i = 0; i < 10; i++) {
            int index = player.getIndexOfCardToDraw();
            assertTrue(index >= 0 && index < 4);
            assertTrue(hand[index].getValue() != 1);
        }
    }

}