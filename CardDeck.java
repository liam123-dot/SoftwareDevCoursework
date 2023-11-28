import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
private Queue<Card> deck = new LinkedList<>();
    private int deckNumber;

    public CardDeck(int n) {
        this.deckNumber = n;
    }

    public synchronized void addCardToDeck(Card card) {
        deck.add(card); // Adds card to the end of the deck
    }

    public synchronized Card drawCardFromDeck() {
        Card card = deck.poll(); // Removes and returns the card at the start of the deck
        
        return card;

    }

    public void outputFinalDeck() {
        try {
            FileWriter writer = new FileWriter("deck" + deckNumber + "_output.txt");
            writer.write(toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to create deck" + deckNumber + "_output.txt");
        }
        
    }

    public String toString() {
        String deckString = "Deck" + deckNumber + " contents: ";
        for (Card card : deck) {
            deckString += card.toString() + " ";
        }
        return deckString;
    }
    public int getDeckNumber() {
        return deckNumber;
    }
}