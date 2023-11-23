// player must not hold onto a non-preferred denomination card indefinitely, so you
// must implement your Player class to reflect this restriction

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class Player implements Runnable{

    // each player has a private hand of cards
    private Card[] hand = new Card[4];
    private CardDeck previousDeck;
    private CardDeck nextDeck;
    private int playerNumber;

    //player class    

    public Player(int n, CardDeck previousDeck, CardDeck nextDeck) {
        // constructor
        this.playerNumber = n;
        this.previousDeck = previousDeck;
        this.nextDeck = nextDeck;
    }

    public void run() {
        initialHandOutput(Arrays.toString(hand));

        // run method
        while (true) {
            Card drawnCard = previousDeck.drawCardFromDeck();
            drawOutput(drawnCard.getValue(), previousDeck.getDeckNumber());
        
            if (drawnCard.getValue() != playerNumber) {
                Card discardedCard = hand[0];
                nextDeck.addCardToDeck(discardedCard); // Add to the bottom of the next deck
                discardOutput(discardedCard.getValue(), nextDeck.getDeckNumber());
        
                hand[0] = drawnCard;
                currentHandOutput(Arrays.toString(hand));
            }
        
            if (checkHasWon()) {
                gameResultOutput(true, Arrays.toString(hand));
                return;
            }
        }
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public Card[] getHand() {
        return hand;
    }

    public String toString() {
        // print player number and hand
        return "Player " + playerNumber + " hand: " + java.util.Arrays.toString(hand);
    }

    public synchronized void addCardToHand(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                return;
            }
        }
    }

    private boolean checkHasWon() {
        // checks if the 4 cards in hand all have the same value
        // iterate though hand if one is null return false
        // if all are the same return true
        if (hand[0] == null) {
            return false;
        }
    
        int value = hand[0].getValue();
    
        for (Card card : hand) {
            if (card == null || card.getValue() != value) {
                return false;
            }
        }
    
        return true;
    }
    private void writeToPlayerFile(String action) {
    try (FileWriter writer = new FileWriter("player" + playerNumber + "_output.txt", true)) {
        writer.write(action + "\n");
    } catch (IOException e) {
        System.out.println("Failed to write to player" + playerNumber + "_output.txt");
    }
    }

    public void initialHandOutput(String hand) {
        writeToPlayerFile("player " + playerNumber + " initial hand " + hand);
    }

    public void drawOutput(int value, int previousDeck) {
        writeToPlayerFile("player " + playerNumber + " draws a " + value + " from deck " + previousDeck);
    }

    public void discardOutput(int value, int nextDeck) {
        writeToPlayerFile("player " + playerNumber + " discards a " + value + " to deck " + nextDeck);
    }

    public void currentHandOutput(String currentHand) {
        writeToPlayerFile("player " + playerNumber + " current hand is " + currentHand);
    }

    public void gameResultOutput(boolean checkHasWon, String finalHand) {
        writeToPlayerFile("player " + playerNumber + (checkHasWon ? " wins" : " exits"));
        writeToPlayerFile("player " + playerNumber + " final hand: " + finalHand);
    }

}