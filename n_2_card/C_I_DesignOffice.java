package n_2_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_I_DesignOffice extends Card {

	public C_I_DesignOffice() {
		super("設計事務", 8, 1, "n");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		List<Card> card5 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int r = new Random().nextInt(board.getDeck().getCards().size());
			card5.add(board.getDeck().getCards().get(r));
			System.out.print("("+(i+1)+") "+card5.get(i));
		}
		System.out.println("");
		int number = 0;
		boolean onRoop = true;
		while(onRoop) {
			try {
				System.out.println("5枚の中から一枚手札に加えるカードを選んでください");
				int num = new java.util.Scanner(System.in).nextInt();
				number = num-1;
				if(number < 0 || number+1 > card5.size()) {
					M.rengeOver();continue;
				}else if(!(number < 0 || number+1 > card5.size())) {
					player.plusCards(card5.remove(number));
					card5.clear();
					onRoop = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				M.DifferentType(e);
				M.retry();
				continue;
			}
		}
		return false;
	}

}
