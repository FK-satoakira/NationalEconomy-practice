package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_I_Factory extends C_Draw {

	public C_I_Factory() {
		super("工場", 12, 2, "I");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		if(player.getCards().size()<2) {M.CardsNotEnough(); return true;}
		for (int i = 0; i < 2; i++) {
			if(2-i > 0) {
				M.CardChoiceTrash(2-i);
			}
			int num = P.SelectFromPlayerCard(player, board);
			board.plusTrashCard(player.getCards().remove(num));
		}
		boolean useble = true;
		for (int i = 0; i < 4; i++) {
			useble = super.secondAbility(player, board, processor);
		}
		return useble;
	}

}
