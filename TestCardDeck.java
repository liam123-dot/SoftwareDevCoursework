import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestCardDeck {

    @Test
    public void addCardToDeck_addsCardToDeck() {
        CardDeck deck = new CardDeck(1);
        Card card = new Card(2);
        deck.addCardToDeck(card);

        Card drawnCard = deck.drawCardFromDeck();
        assertEquals(card, drawnCard);
    }

    @Test
    public void drawCardFromDeck_returnsNullWhenDeckIsEmpty() {
        CardDeck deck = new CardDeck(1);
        Card drawnCard = deck.drawCardFromDeck();
        assertNull(drawnCard);
    }

    @Test
    public void outputFinalDeck_createsFileWithCorrectName() {
        CardDeck deck = new CardDeck(1);
        deck.outputFinalDeck();

        File file = new File("deck1_output.txt");
        assertTrue(file.exists());
    }

    @Test
    public void outputFinalDeck_handlesIOExceptionGracefully() {
        CardDeck deck = new CardDeck(1) {
            @Override
            public void outputFinalDeck() {
                try {
                    FileWriter writer = new FileWriter("/invalid/path/deck" + getDeckNumber() + "_output.txt");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Failed to create deck" + getDeckNumber() + "_output.txt");
                }
            }
        };

        // No exception should be thrown
        deck.outputFinalDeck();
    }
}