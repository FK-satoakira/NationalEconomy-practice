package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A_Farm extends C_A {

	public C_A_Farm() {
		super("農場", 6, 1, "A");
	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		for (int i = 0; i < 2; i++) {
			super.secondAbility(player, board, processor);
		}
		return false;
	}

}
