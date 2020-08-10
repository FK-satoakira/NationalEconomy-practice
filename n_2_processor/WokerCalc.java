package n_2_processor;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_board.Woker;
import n_2_card.Card;

public class WokerCalc {

	public WokerCalc() {
	}

	public static void W_calc(Board board, Player player) {
		List<Card> bc = board.getCards();
		for (int i = 0; i < bc.size(); i++) {
			switch(bc.get(i).getName()) {
				case "学校":
					if(!bc.get(i).isOn()) {
						player.plusWokers(new Woker());
					}
					break;
				case "高等学校":
					if(!bc.get(i).isOn()) {
						player.setWokers(4);
					}
					break;
				case "大学":
					if(!bc.get(i).isOn()) {
						player.setWokers(5);
					}
					break;
			}

		}
	}
}
