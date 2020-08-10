package n_2_card;

import java.util.Random;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_Draw extends Card {

	public C_Draw(String name, int price, int trash, String type) {
		super(name, price, trash, type);

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		int r = new Random().nextInt(board.getDeck().getCards().size());
		player.plusCards(board.getDeck().getCards().remove(r));
		return false;
	}

}
