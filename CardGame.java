import java.util.Scanner;
import java.io.File;

public class CardGame {

    public static int[] loadDeck(String fileLocation, int numberOfPlayers){
        // takes the file location. Verifys it has 8n lines that are all ints

        try {

            File deck = new File(fileLocation);
            Scanner deckScanner = new Scanner(deck);

            int[] deckArray = new int[8*numberOfPlayers];

            int i = 0;
            while (deckScanner.hasNextLine()) {
                String data = deckScanner.nextLine();
                try {
                    deckArray[i] = Integer.parseInt(data);
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
        // First requests the inputs of how many players, takes the input which must be an integer
        // Then Asks for the location of the text file which is the deck.
        // The deck must have 8n lines where n is the number of players
        // and all the line must be integers

        // Then the program will create the players and deal the cards

        Scanner input = new Scanner(System.in);
        System.out.println("How many players are there?");

        int numberOfPlayers = input.nextInt();
        System.out.println("Where is the deck?");
        String deckLocation = input.next();

        int deck[] = loadDeck(deckLocation, numberOfPlayers);

        System.out.println(deck[0]);

    }
}