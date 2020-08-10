package n_2_card;

import java.util.List;
import java.util.Random;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_B_GeneralContractor extends C_B {

	public C_B_GeneralContractor() {
		super("ゼネコン", 18, 4, "n");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
//		if(player.getCards().size()==0) {System.out.println("手札がありません");return true;}

		boolean useble = super.secondAbility(player, board, processor);
		if(useble) {
			return useble;
		}
		for (int i = 0; i < 2; i++) {

			List<Card> deckCard = board.getDeck().getCards();
			int r = new Random().nextInt(deckCard.size());
			player.plusCards(deckCard.remove(r));
		}
		return useble;
	}

}
