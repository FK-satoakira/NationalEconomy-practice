package n_2_card;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_A_Pioneer extends Card {

	public C_A_Pioneer() {
		super("開拓民", 14, 2, "n");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		List<Card> pc = player.getCards();
		int count = 0;
		for (int i = 0; i < pc.size(); i++) {
			if(pc.get(i).getType().equals("A")) {
				count++;
			}
		}
		if(count==0){System.out.println("手札にタイプ「A」のカードはありません");return true;}

//		while() {
//
//		}
		System.out.println("建設する、タイプ「A」のカードを選択してください");

		int buildAgr = P.SelectFromPlayerCard(player, board);
		if(!pc.get(buildAgr).getType().equals("A")){System.out.println("タイプ「A」の以外は建設できません");return true;}
		player.plusBoardOfPlayer(pc.remove(buildAgr));
		return false;
	}

}
