package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_F_CoffeeShop extends Card {

	public C_F_CoffeeShop() {
		super("珈琲店", 8, 1, "n");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		player.plusMoney(-board.getHousehold().plusMoney(-5));
		return false;
	}

}
