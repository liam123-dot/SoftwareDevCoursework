// player must not hold onto a non-preferred denomination card indefinitely, so you
// must implement your Player class to reflect this restriction

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
        // run method
        while (true){

            boolean hasWon = this.checkHasWon();
            if (hasWon){
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

    public void addCardToHand(Card card) {
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
}