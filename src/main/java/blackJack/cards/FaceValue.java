package blackJack.cards;

public enum FaceValue {
    A(11), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), J(10), K(10);

    private final int value;
    FaceValue(int value) {
        this.value = value;
    }

}
