package blackJack;

import blackJack.cards.Card;
import blackJack.cards.FaceValue;
import blackJack.cards.Suit;

import java.util.ArrayList;
import java.util.List;

public class Desk {
    private final List<Card> cards;
    private int end; // the end index of available cards

    public Desk(int set) { // pass in the set of Cards to use
        cards = createCards(set);
        shuffle();
        end = cards.size() - 1;
    }

    public void shuffle() {
        // [0, 1) * (size - i) + i = [i, size)
        for (int i = 0; i < cards.size(); i++) {
            int idx = (int) (Math.random() * (cards.size() - i) + i);
            swap(cards, i, idx);
        }
    }

    private void swap(List<Card> cards, int i, int j) {
        Card tmp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, tmp);
    }

    public List<Card> dealHands(int number) {
        if (number > end + 1) {
            return new ArrayList<>(); // no enough cards to deal
        }
        List<Card> results = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            results.add(dealCard());
        }
        return results;
    }

    public Card dealCard() {
        if (end < 0) {
            return null; // no card to deal
        }
        Card dealt = cards.get(end);
        end--;
        return dealt;
    }

    public int remainingCards() {
        return end + 1;
    }

    public boolean isEmpty() {
        return end < 0;
    }

    private List<Card> createCards(int set) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < set; i++) {
            for (Suit suit: Suit.values()) {
                for (FaceValue faceValue: FaceValue.values()) {
                    cards.add(new Card(faceValue, suit));
                }
            }
        }
        return cards;
    }
}
