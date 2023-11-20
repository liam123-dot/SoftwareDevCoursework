import java.util.Scanner;
import java.io.File;

public class CardGame {

    public static Card[] loadDeck(String fileLocation, int numberOfPlayers){
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

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int numberOfPlayers = getNumberOfPlayers(scanner);
        String deckLocation = getDeckLocation(scanner);

        Card deck[] = loadDeck(deckLocation, numberOfPlayers);

        System.out.println(deck[0]);

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