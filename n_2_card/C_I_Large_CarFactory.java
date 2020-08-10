package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_I_Large_CarFactory extends C_I_CarFactory {

	public C_I_Large_CarFactory() {
		super();
//		this.setName("大型自動車工場");
		this.name = "大型自動車工場";
	}
	public void superability(Player player, Board board, P processor) {
		secondAbility(player, board, processor);
	}
}
