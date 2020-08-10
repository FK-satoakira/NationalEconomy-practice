package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_I_ChemicalFactory extends C_Draw {

	public C_I_ChemicalFactory() {
		super("化学工場", 18, 4, "I");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		boolean useble = true;
		if(player.getCards().size()==0) {
			for (int i = 0; i < 4; i++) {
				useble = super.secondAbility(player, board, processor);
			}
		}else {
			for (int i = 0; i < 2; i++) {
				useble = super.secondAbility(player, board, processor);
			}
		}
		return useble;
	}

}
