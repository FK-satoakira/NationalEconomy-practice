package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_board.Woker;
import n_2_processor.P;

public class C_P_Vocational extends Card {

	public C_P_Vocational() {
		super("専門学校", 0, 0, "P");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		if(player.getWokers().size()>=player.getWokerlimit()) {
			System.out.println("これ以上労働者は増やせません");return true;
		}
		player.plusWokers(new Woker());
		return false;
	}

}
