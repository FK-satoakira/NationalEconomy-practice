package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_Facility extends Card {

	public C_Facility(String name, int price, int trash) {
		super(name, price, trash, "F");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		System.out.println("施設カードは使用するものではありません");
		return true;
	}

}
