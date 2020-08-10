package n_2_processor;

import n_2_board.Board;
import n_2_board.Player;

public class P {

	public P() {
	}

//	手札からカードを選択
	public static int SelectFromPlayerCard(Player player, Board board) {
		int number = 0;
		boolean onRoop = true;
		while(onRoop) {

			try {
				M.CardChoice();
				M.playerCard(player);/*手札を表示*/
				int num = new java.util.Scanner(System.in).nextInt();
				number = num-1;
				if(number < 0 || number+1 > player.getCards().size()) {
					M.rengeOver();continue;
				}else if(!(number < 0 || number+1 > player.getCards().size())) {
					onRoop = false;return number;
				}
			} catch (Exception e) {
				e.printStackTrace();
				M.DifferentType(e);
				M.retry();
				System.out.println("");
				M.playerCard(player);
				continue;
			}
		}
		return number;
	}

//	ボードからカードを選択
	public static int SelectFromBoard(Player player, Board board) {
		int number = 0;
		boolean onRoop = true;
		while(onRoop) {
			try {
				int num = new java.util.Scanner(System.in).nextInt();
				number = num-1;
				if(number < 0 || number+1 > board.getCards().size()) {
					M.rengeOver();continue;
				}else if(!(number < 0 || number+1 > board.getCards().size())) {
					onRoop = false;return number;
				}
			} catch (Exception e) {
				e.printStackTrace();
				M.DifferentType(e);
				M.retry();
				System.out.println("");
				M.board(board);
				continue;
			}
		}
		return number;
	}
//	プレイヤーボードからカードを選択
	public static int SelectFromPlayerBoard(Player player, Board board) {
		int number = 0;
		boolean onRoop = true;
		while(onRoop) {

			try {
				int num = new java.util.Scanner(System.in).nextInt();
				number = num-1;
				if(number < 0 || number+1 > player.getBoardOfPlayer().getCards().size()) {
					M.rengeOver();continue;
				}else if(!(number < 0 || number+1 > player.getBoardOfPlayer().getCards().size())) {
					onRoop = false;return number;
				}
			} catch (Exception e) {
				e.printStackTrace();
				M.DifferentType(e);
				M.retry();
				System.out.println("");
				M.playerBoard(player);
				continue;
			}
		}
		return number;
	}
//	ボードかプレイヤーボードかを選択
	public static int GameBoard_Or_PlayerBoard(Player player, Board board) {
		int number = 0;
		boolean onRoop = true;
		while(onRoop) {
			try {
				int num = new java.util.Scanner(System.in).nextInt();
				number = num-1;
				if(number < 0 || number > 1) {
					M.rengeOver();continue;
				}else if(!(number < 0 || number > 1)) {
					onRoop = false;return number;
				}
			} catch (Exception e) {
				e.printStackTrace();
				M.DifferentType(e);
				M.retry();
				System.out.println("");
				M.Board_Or_PlayerBoard();
				continue;
			}
		}
		return number;
	}
}
