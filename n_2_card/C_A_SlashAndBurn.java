package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A_SlashAndBurn extends C_A {

	public C_A_SlashAndBurn() {
		super("焼畑", 0, 0, "A");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		for (int i = 0; i < 5; i++) {
			super.secondAbility(player, board, processor);
		}
		return false;
	}

}
