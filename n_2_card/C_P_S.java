package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_P_S extends Card {
	private boolean useble = true;
	public C_P_S(String name, int price, int trash, String type) {
		super(name, price, trash, type);

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {

		return false;
	}

}
