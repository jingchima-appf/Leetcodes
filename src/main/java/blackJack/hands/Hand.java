package blackJack.hands;

import blackJack.Desk;
import blackJack.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
    protected final Desk desk;
    protected List<Card> cards;

    public Hand(Desk desk) {
        this.desk = desk;
        this.cards = new ArrayList<>();
    }

    public abstract int score();

    public Card addCard() {
        Card card = desk.dealCard();
        if (card != null) {
            this.cards.add(card);
        }
        return card;
    }

}
