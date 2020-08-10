package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public abstract class C_E extends Card {

	public C_E(String name, int price, int trash, String type) {
		super(name, price, trash, type);

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		int pCard = choice();
		if(pCard*6 > board.getHousehold().getMoney()){System.out.println("家計が足りません");return true;}
		if(player.getCards().size() < pCard){M.CardChoiceTrash(pCard);return true;}
		for (int i = 0; i < pCard; i++) {
			int num = P.SelectFromPlayerCard(player, board);
			board.plusTrashCard(player.getCards().remove(num));
			player.plusMoney(-board.getHousehold().plusMoney(-6));
		}
		System.out.println("player.getMoney()="+player.getMoney());
		System.out.println("board.getHousehold()="+board.getHousehold().getMoney());

		return false;
	}
	protected abstract int choice() ;

}
