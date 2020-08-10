package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_P_Carpenter extends C_B {

	public C_P_Carpenter() {
		super("大工", 0, 0, "P");

	}

	@Override
	public boolean ability(Player player, Board board, P processor) {

		return super.ability(player, board, processor);
	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {

		return super.secondAbility(player, board, processor);
	}

}
