import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class CardGame {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int numberOfPlayers = getNumberOfPlayers(scanner);
        String deckLocation = getDeckLocation(scanner);

        Card[] deck = loadDeck(deckLocation, numberOfPlayers);
        
        CardDeck[] decks = getDecks(numberOfPlayers);
        Player[] players = getPlayers(numberOfPlayers, decks);

        dealToPlayers(deck, players);
        dealToDecks(deck, decks);

        startPlayers(players);

    }

    public static void dealToPlayers(Card[] deck, Player[] players) {
        // deal cards to players
        int numberOfPlayers = players.length;

        for (int i = 0; i < deck.length / 2; i++) {
            players[i % numberOfPlayers].addCardToHand(deck[i]);
        }
    }

    public static void dealToDecks(Card[] deck, CardDeck[] decks) {
        // deal cards to decks
        int numberOfDecks = decks.length;
        
        int midPoint = deck.length/2;

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

    private static Player[] getPlayers(int numberOfPlayers, CardDeck[] decks) {
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i + 1, decks[i], decks[(i + 1) % numberOfPlayers]);
        }
        return players;
    }

    private static CardDeck[] getDecks(int numberOfDecks) {

        CardDeck[] decks = new CardDeck[numberOfDecks];
        for (int i = 0; i < numberOfDecks; i++) {
            decks[i] = new CardDeck(i + 1);
        }
        return decks;

    }

    private static int getNumberOfPlayers(Scanner scanner) {
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
        System.out.println("Where is the deck?");
        return scanner.next();
    }

}