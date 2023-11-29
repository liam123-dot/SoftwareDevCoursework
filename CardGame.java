import java.util.Scanner;
import java.io.File;

public class CardGame {
    // Main class for the card game

    public static void main(String[] args) {
        // Main method to start the game

        Scanner scanner = new Scanner(System.in);

        // Get the number of players and the location of the deck
        int numberOfPlayers = getNumberOfPlayers(scanner);
        String deckLocation = getDeckLocation(scanner);

        // Load the deck and create the decks and players
        Card[] deck = loadDeck(deckLocation, numberOfPlayers);
        CardDeck[] decks = getDecks(numberOfPlayers);
        GameState gameState = new GameState(decks);
        Player[] players = getPlayers(numberOfPlayers, decks, gameState);

        // Deal the cards to the players and the decks
        dealToPlayers(deck, players);
        dealToDecks(deck, decks);

        // Check if any player has won at the start of the game
        for (Player player : players) {
            player.checkHasWon();
        }

        // Start the player threads
        startPlayers(players);
    }

    // Method to deal cards to players
    public static void dealToPlayers(Card[] deck, Player[] players) {
        int numberOfPlayers = players.length;
        // the first half of the deck is dealt to the players
        for (int i = 0; i < deck.length / 2; i++) { 
            players[i % numberOfPlayers].addCardToHand(deck[i]);
        }
    }

    // Method to deal cards to decks
    public static void dealToDecks(Card[] deck, CardDeck[] decks) {
        int numberOfDecks = decks.length;
        int midPoint = deck.length/2;   
        // the second half of the deck is dealt to the decks
        for (int i = midPoint; i < deck.length; i ++){
            decks[i%numberOfDecks].addCardToDeck(deck[i]);
        }
    }

    private static Card[] loadDeck(String fileLocation, int numberOfPlayers){
        // takes the file location. Verifys it has 8n lines that are all ints

        try {

            File deck = new File(fileLocation);
            Scanner deckScanner = new Scanner(deck);

            Card[] deckArray = new Card[8*numberOfPlayers];

            int i = 0;  
            while (deckScanner.hasNextLine()) {
                String data = deckScanner.nextLine();
                try {
                    deckArray[i] = new Card(Integer.parseInt(data));
                } catch (NumberFormatException e) {
                    System.out.println("The deck file is not formatted correctly");
                    System.exit(0);
                }
                i++;
            }
            deckScanner.close();
            return deckArray;

        } catch (Exception e) {
            System.out.println("The file does not exist in the specified location");
            System.exit(0);
        }
        
        return null;
    }

    private static void startPlayers (Player[] players) {
        // each Player object implements runnable

        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }
    
    }

    private static Player[] getPlayers(int numberOfPlayers, CardDeck[] decks, GameState gameState) {
        // Creates an array of Player instances, each associated with two specific decks and the game state.
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i + 1, decks[i], decks[(i + 1) % numberOfPlayers], gameState);
        }
        return players;
    }

    private static CardDeck[] getDecks(int numberOfDecks) {
        // Creates and returns an array of CardDeck instances based on the specified number.
        CardDeck[] decks = new CardDeck[numberOfDecks];
        for (int i = 0; i < numberOfDecks; i++) {
            decks[i] = new CardDeck(i + 1);
        }
        return decks;

    }

    private static int getNumberOfPlayers(Scanner scanner) {
        // get the number of players and checks validity
        while (true) {
            try {
                System.out.println("How many players are there?");

                int numberOfPlayers = scanner.nextInt();
                if (numberOfPlayers > 0) {
                    return numberOfPlayers;
                } else {
                    System.out.println("Please enter a valid number of players (greater than 0).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.nextLine(); 
            }
        }
    }

    private static String getDeckLocation(Scanner scanner) {
        // get the location of the deck file
        System.out.println("Where is the deck?");
        return scanner.next();
    }

}