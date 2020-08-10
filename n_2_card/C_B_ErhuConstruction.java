package n_2_card;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_B_ErhuConstruction extends Card{

	public C_B_ErhuConstruction() {
		super("二胡建設", 20 , 5, "4");
	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		System.out.println("このカードは捨て札数が同じなら２つ同時に建設できます");
		System.out.println("１枚目のカードを選択してください");
		List<Card> plalyerCard = player.getCards();
		int FBCnum = P.SelectFromPlayerCard(player, board);
		if (plalyerCard.get(FBCnum).getTrash() + 2 > player.getCards().size()) {
			M.BuildedCannot(plalyerCard.get(FBCnum));return true;
		}
		Card FBC = plalyerCard.remove(FBCnum);
		System.out.println("２枚目のカードを選択してください");
		int SBCnum = 0;
		boolean success = false;
		while(!success) {
			System.out.println("２枚目のカードを選択してください");
			SBCnum = P.SelectFromPlayerCard(player, board);
			if(plalyerCard.get(SBCnum).getTrash() != FBC.getTrash()) {
				System.out.println("捨て札数が違います");M.retry();continue;
			}else {
				success = true;
			}
		}
		Card SBC = plalyerCard.remove(SBCnum);
		for (int i = 0; i < FBC.getTrash(); i++) {

			M.CardChoiceTrash(FBC.getTrash()-i);
			int trashNum = P.SelectFromPlayerCard(player, board);
			board.plusTrashCard(player.getCards().remove(trashNum));
		}
		player.plusBoardOfPlayer(FBC);
		player.plusBoardOfPlayer(SBC);
		System.out.println(FBC.getName()+"と"+SBC.getName()+"を建設しました");
		return false;
	}

}
