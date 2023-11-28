class Card {
    //Card class   

    private int value;

    public Card(int value) {
        this.value = value;
    }

    public String toString() {
    // Returns card value as string
        return Integer.toString(value);
    }

    public int getValue() {
    // Returns card value
        return value;
    }

}

