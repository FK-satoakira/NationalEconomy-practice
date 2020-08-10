package n_2_board;

import java.util.ArrayList;
import java.util.List;

import n_2_card.Card;

public class Deck {
	private List<Card> cards = new ArrayList<>();

	public Deck() {

	}
	public List<Card> getCards() {
		return cards;
	}
	public void plusCards(Card card) {
		this.cards.add(card);
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

}
