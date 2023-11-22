import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private Queue<Card> deck = new LinkedList<>();
    private int deckNumber;

    public CardDeck(int n) {
        this.deckNumber = n;
    }

    public void addCardToDeck(Card card) {
        deck.add(card); // Adds card to the end of the deck
    }

    public Card drawCardFromDeck() {
        return deck.poll(); // Removes and returns the card at the start of the deck
    }

    public String toString() {
        String deckString = "Deck " + deckNumber + ": ";
        for (Card card : deck) {
            deckString += card.toString() + " ";
        }
        return deckString;
    }
}