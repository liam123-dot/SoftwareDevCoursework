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
        System.out.println("Deck " + deckNumber + ": Contents: " + deck.toString());
    }

    public synchronized Card drawCardFromDeck() {
        Card card = deck.poll(); // Removes and returns the card at the start of the deck
        
        System.out.println("Deck " + deckNumber + ": Contents: " + deck.toString());

        return card;

    }

    public String toString() {
        String deckString = "Deck " + deckNumber + ": ";
        for (Card card : deck) {
            deckString += card.toString() + " ";
        }
        return deckString;
    }
    public int getDeckNumber() {
        return deckNumber;
    }
}