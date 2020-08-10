package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_I_CarFactory extends C_Draw {


	public C_I_CarFactory() {
		super("自動車工場", 24, 5, "I");

	}
	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		if(player.getCards().size()<3) {M.CardsNotEnough(); return true;}
		System.out.println("このカードは手札を３枚捨て、７枚引くことができます");
		for (int i = 0; i < 3; i++) {
			if(3-i > 0) {
				M.CardChoiceTrash(3-i);
			}
			int num = P.SelectFromPlayerCard(player, board);
			board.plusTrashCard(player.getCards().remove(num));
		}
		boolean useble = true;
		for (int i = 0; i < 7; i++) {
			useble = super.secondAbility(player, board, processor);
		}

		return useble;
		}
	
	public void q() {
		
	}
	
	

}
