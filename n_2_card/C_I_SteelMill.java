package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public class C_I_SteelMill extends C_Draw {

	public C_I_SteelMill() {
		super("製鉄所", 20, 4, "I");

	}

	@Override
	protected boolean secondAbility(Player player, Board board, P processor) {
//		何故これで三枚引けるか謎?↓
//		A. return boolean useble = true;って記述してたから
		boolean useble = true;
		for (int i = 0; i < 3; i++) {
			M.test("C_I_SteelMill");
			useble = super.secondAbility(player, board, processor);
		}
		return useble;
	}

}
