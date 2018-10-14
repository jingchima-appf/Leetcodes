package blackJack.hands;

import blackJack.Desk;
import blackJack.cards.Card;
import blackJack.cards.FaceValue;

import java.util.Iterator;
import java.util.TreeSet;

public class BlackJackHand extends Hand {

    private TreeSet<Integer> scores;

    public BlackJackHand(Desk desk) {
        super(desk);
        scores = new TreeSet<>();
    }

    @Override
    // return the maximum score that not exceeds 21
    public int score() {
        return 1;
    }

    @Override
    public Card addCard() {
        Card card = super.addCard();
        this.updateScore(card);
        return card;
    }

    private void updateScore(Card card) {
        if (card.faceValue() == FaceValue.A) {
            // wrong! Could not modify the collection while iterating!!
//            Iterator<Integer> iterator = scores.iterator();
//            while (iterator.hasNext()) {
//                int val = iterator.next();
//                scores.add(val + 1);
//                scores.add(val + 11);
//            }
        }
    }
}
