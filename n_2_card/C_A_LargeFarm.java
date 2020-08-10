package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A_LargeFarm extends C_A {

	public C_A_LargeFarm() {
		super("大農園", 12, 3, "A");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		for (int i = 0; i < 3; i++) {
			super.secondAbility(player, board, processor);
		}
		return false;
	}

}
