// player must not hold onto a non-preferred denomination card indefinitely, so you
// must implement your Player class to reflect this restriction

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class Player implements Runnable{

    // each player has a private hand of cards
    private Card[] hand = new Card[4];
    private CardDeck previousDeck;
    private CardDeck nextDeck;
    private int playerNumber;
    
    private GameState gameState;

    //player class    

    public Player(int n, CardDeck previousDeck, CardDeck nextDeck, GameState gameState) {
        // constructor
        this.playerNumber = n;
        this.previousDeck = previousDeck;
        this.nextDeck = nextDeck;
        this.gameState = gameState;
        createPlayerFile();
    }

    public void run() {
        // run method
        while (true) {
            Boolean gameWon = this.gameState.getGameWon();
            if (gameWon) {
                if (this.gameState.getWinner() != this) {
                    gameResultOutput(false);
                }
                return;
            }
            Card drawnCard = previousDeck.drawCardFromDeck();
            if (drawnCard == null) {    
                return;
            }
            drawOutput(drawnCard.getValue(), previousDeck.getDeckNumber());
        
            int indexToDraw = getIndexOfCardToDraw();

            Card discardedCard = hand[indexToDraw];
            nextDeck.addCardToDeck(discardedCard); // Add to the bottom of the next deck
            discardOutput(discardedCard.getValue(), nextDeck.getDeckNumber());
    
            hand[indexToDraw] = drawnCard;
            currentHandOutput(Arrays.toString(hand));
    
        
            if (checkHasWon()) {
                return;
            }
        }
    }

    public int getIndexOfCardToDraw() {
        Random random = new Random();
        while (true) {
            int indexCounter = random.nextInt(4); // Generates a random number between 0 (inclusive) and 4 (exclusive)

            if (this.hand[indexCounter].getValue() != playerNumber) {
                return indexCounter;
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
                if (i == hand.length - 1) {
                    initialHandOutput(Arrays.toString(hand));
                }
                return;
            }
        }

    }

    public boolean checkHasWon() {
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

        this.gameState.setGameWon(this);
        gameResultOutput(true);

        System.out.println("Player " + playerNumber + " has won");
    
        return true;
    }

    private void createPlayerFile() {
        try {
            FileWriter writer = new FileWriter("player" + playerNumber + "_output.txt", false);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to create player" + playerNumber + "_output.txt");
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
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

    public void gameResultOutput(boolean checkHasWon) {
        if (checkHasWon) {
            writeToPlayerFile("player " + playerNumber + " wins");
        } else {
            writeToPlayerFile("player " + this.gameState.getWinner().getPlayerNumber() + " informed player " + playerNumber + " that player " + this.gameState.getWinner().getPlayerNumber() + " has won");
        }
        writeToPlayerFile("player " + playerNumber + " exits");
        writeToPlayerFile("player " + playerNumber + " final hand is " + Arrays.toString(hand));

    }

}