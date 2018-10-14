package blackJack.cards;

public class Card {
    private final FaceValue faceValue;
    private final Suit suit;

    public Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    // don't forget getter and setter
    public FaceValue faceValue() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }
}

