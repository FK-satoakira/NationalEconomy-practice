package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A extends Card {

	public C_A(String name, int price, int trash, String type) {
		super(name, price, trash, type);

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		player.plusCards(new C_C_Consumer());
		return false;
	}

}
