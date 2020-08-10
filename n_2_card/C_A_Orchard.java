package n_2_card;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A_Orchard extends C_A {

	public C_A_Orchard() {
		super("果樹園", 10, 2, "A");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		List<Card> Pcard = player.getCards();
			if(Pcard.size()>=4) {
				System.out.println("手札が4枚以上あるので使えません");return true;
			}else {
				while(Pcard.size()<4) {
					super.secondAbility(player, board, processor);
				}
			}
		return false;
	}

}
