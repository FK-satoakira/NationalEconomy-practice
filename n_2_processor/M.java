package n_2_processor;

import n_2_board.Board;
import n_2_board.Player;
import n_2_card.Card;

public class M {

	public M() {
	}
	public static void playerCard(Player player) {
		System.out.print("[手札]: ");
		for (int i = 0; i < player.getCards().size(); i++) {
			System.out.print("("+(i+1)+")"+player.getCards().get(i)+", ");
		}
		System.out.println("");
	}
	public static void board(Board board) {
		System.out.print("[ボード]: ");
		for (int i = 0; i < board.getCards().size(); i++) {
			System.out.print("("+(i+1)+")"+board.getCards().get(i)+", ");
		}
		System.out.println("");
	}
	public static void playerBoard(Player player) {
		System.out.print("[プレイヤーボード]: ");
		for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {
			System.out.print("("+(i+1)+")"+ player.getBoardOfPlayer().getCards().get(i)+", ");
		}
		System.out.println("");
	}
//	カード
	public static void CardChoiceTrash(int trash) {
		System.out.println("捨て札を"+trash+"枚選択して下さい");
	}
	public static void CardChoice() {
		System.out.println("カードを選択してください");
	}
	public static void Choicenot() {
		System.out.println("このカードは選択できません");
	}
	public static void CardsNotEnough() {
		System.out.println("手札が足りません");
	}
	public static void sell_Card() {
		System.out.println("カードを売ってください");
	}
//	----
//	建設系
	public static void BuildChoice() {
		System.out.println("建設するカードを選択してください");
	}
	public static void BuildPlans(Card card) {
		System.out.println(card.getName()+"を建設します");
	}
	public static void Builded(Card card) {
		System.out.println(card.getName()+"を建設しました");
	}
	public static void BuildedCannot(Card card) {
		System.out.println(card.getName()+"を建設するにはカードが足りません");
	}
//	-----
//	想定外入力
	public static void rengeOver() {
		System.out.println("範囲内の数値を入力してください");
	}
	public static void DifferentType(Exception e) {
		System.out.println("型が違います"+e);
	}
//	-----
//	やり直し
	public static void retry() {
		System.out.println("もう一度選んでください");
	}

	public static void Board_Or_PlayerBoard() {
		System.out.println("［ボード］を使用する場合は[1]、");
		System.out.println("［マイボード］を使用する場合は[2]を選択してください");
	}
//	資金
	public static void moneyNotEnough(int m) {
		System.out.println("お金が足りません。"+m+"ドル分のカードを売ってください");
	}
	public static void test(String str) {
		System.out.println(str);
	}
	public static void test() {
		System.out.println("test");
	}
}
