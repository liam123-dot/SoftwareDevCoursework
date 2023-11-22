import java.io.File;
import java.util.Scanner;


class Card {
    //card class   

    private int value;

    public Card(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }

}

