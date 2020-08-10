package n_2_card;

import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.P;

public class C_P_University extends Card {

	public C_P_University() {
		super("大学", 0, 0, "P");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
		List<Card> bc = board.getCards();
		boolean Ucard = true;
		boolean Scard = true;

		System.out.println("Ucard"+Ucard);
		for (int i = 0; i < bc.size(); i++) {
			if(bc.get(i).getName().equals("学校"))
				Ucard = bc.get(i).isOn();
		}
		System.out.println(Ucard);
		if(player.getWokers().size()>=4 && !Ucard) {
			System.out.println("既に労働者は5人以上います");return true;
		}

		if(player.getWokers().size()>=5) {
			System.out.println("既に労働者は5人以上います");return true;
		}
		System.out.println("次のターンから労働者が5人に増えます");
		return false;
	}

}
