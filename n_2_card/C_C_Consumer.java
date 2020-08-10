package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_C_Consumer extends Card {

	public C_C_Consumer() {
		super("消費財", 0, 0, "0");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {

		return false;
	}

}
