package n_2_card;

import java.util.Random;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_P_Mine extends Card{
	private String name = "";
	private int price = 0;
	private int trash = 0;
	private String type = "";
	public C_P_Mine() {
		super("鉱山", 0, 0, "P");
	}

	@Override
	public boolean ability(Player player, Board board, P processor) {
		int r = new Random().nextInt(board.getDeck().getCards().size());
		player.plusCards(board.getDeck().getCards().remove(r));
		return false;
	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {

		return false;
	}



}
