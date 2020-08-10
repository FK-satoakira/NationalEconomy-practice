package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_F_Restaurant extends Card {

	public C_F_Restaurant() {
		super("レストラン", 16, 3, "n");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		if(player.getCards().isEmpty()) {
			System.out.println("手札がありません");return true;
		}
		int trashCard = P.SelectFromPlayerCard(player, board);
		board.plusTrashCard(player.getCards().remove(trashCard));
		player.plusMoney(-board.getHousehold().plusMoney(-15));
		return false;
	}

}
