package n_2_card;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_B_constructionCompany extends Card {

	public C_B_constructionCompany() {
		super("建設会社", 10, 2, "4");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
//		if(playerCards.size()==0) {System.out.println("手札がありません");return true;}
		if(player.getCards().size()==0) {System.out.println("手札がありません");return true;}
		M.BuildChoice();
		M.playerCard(player);
		int num = P.SelectFromPlayerCard(player, board);
		List<Card> playerCards = player.getCards();
		if(playerCards.get(num).getType().equals("0")) {
			System.out.println("消費財は建設不可です");
			return true;
		}
		if(playerCards.get(num).getTrash()-1 >= player.getCards().size()) {
			System.out.println(playerCards.get(num).getName()+"を建設するには手札が足りません");
			return true;
		}

		Card buildCard = playerCards.remove(num);
		player.plusBoardOfPlayer(buildCard);
		M.BuildPlans(buildCard);
		int count = 0;
		int trashnum = buildCard.getTrash()-1;
		for (int i = 0; i < trashnum; i++) {
			M.CardChoiceTrash(trashnum-count);
			M.playerCard(player);
			int trashCard = P.SelectFromPlayerCard(player, board);
			board.plusTrashCard(player.getCards().remove(trashCard));
			count++;
		}
//		this.setOn(false);
		return false;
	}

}
