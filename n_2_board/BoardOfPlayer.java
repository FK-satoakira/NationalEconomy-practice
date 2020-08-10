package n_2_board;

import java.util.ArrayList;
import java.util.List;

import n_2_card.Card;

public class BoardOfPlayer {
	private List<Card> cards = new ArrayList<>();

	public BoardOfPlayer() {
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void plusCards(Card card) {
		this.cards.add(card);
	}

	@Override
	public String toString() {
		return "[プレイヤーボード]: " + cards;
	}

}
