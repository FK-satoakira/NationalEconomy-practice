package n_2_board;

import java.util.ArrayList;
import java.util.List;

import n_2_card.Card;

public class Trash {
	private List<Card> cards = new ArrayList<>();
	public Trash() {
	}
	public List<Card> getCards() {
		return cards;
	}
	public void plusCard(Card card) {
		this.cards.add(card);
	}
//	public void setCards(List<Card> cards) {
//		this.cards = cards;
//	}
	@Override
	public String toString() {
		return "[トラッシュ]=" + cards;
	}
}
