package n_2_processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import n_2_board.Board;
import n_2_board.Player;
import n_2_card.Card;

public class MoneyCalc {
	public MoneyCalc() {
	}
	public void moneyCalc(Player player, Board board, int turn) {
		List<Card> TemporaryCards = new ArrayList<>();/*仮捨てカード*/
		List<Card> copyPlayerCards = new ArrayList<>(player.getBoardOfPlayer().getCards());/*プレイヤーボードのカード一覧をcopyPlayerCardsに渡してる*/

		//List<Card> pbc = player.getBoardOfPlayer().getCards();
//		フィールド
		int perPerson = 0;/*そのターンの一人当たり給料*/
		if(turn<3) {
			perPerson = 2;
		}else if (turn < 6) {
			perPerson = 3;
		}else if (turn < 8) {
			perPerson = 4;
		}else {
			perPerson = 5;
		}
		int salary = player.getWokers().size()*perPerson;/*支払金額。wokerについてはplayerのフィールドを参照*/
		int shortfall = 0;/*残高*/
		int money = player.getMoney();/*資金*/
		int totalsellCardprice = 0;/*売るカードの値段*/
		boolean only_facility = true;/*施設カードだけならtrue*/
		boolean board_empty = false;
//『salary』と『money』はmoneyCalc()を呼び出した時点で金額が設定される

//		System.out.println("資金 " + player.getMoney());/*今持ってるお金*/

		while (true) {
			//【パターン１ 資金>給料配布額】
			if(money >= salary) {
				System.out.println("パターン1");
				System.out.println("資金から "+salary+" の給料を払います");
				player.plusMoney(-salary);
				board.getHousehold().plusMoney(salary);
				break;/*ここで完結*/
			}else {/*『資金＜給料のパターン』*/
//			【パターン２ 資金<給料配布額	&&	売る土地もない】
//				【プレイヤーボードが施設だけかどうか】
				for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {/*プレイヤーボード全てのカードをループで参照してる*/
					String type = player.getBoardOfPlayer().getCards().get(i).getType();
					if (!type.equals("F")) {/*プレイヤーボードに非施設が１枚でもあれば*/
						only_facility = false;
					}
				}
				if (player.getBoardOfPlayer().getCards().size() == 0) {/*プレイヤーボードが空の場合*/
					board_empty = true;
				}
				if((only_facility && board_empty)) {
					board.getHousehold().plusMoney(money);
					player.plusMoney(-salary);
					System.out.println("パターン2");
					System.out.println("売れるカードがありません。支払えなかった分、 "+player.getDebit()+" 借金が増えます");
					break;/*ここで完結*/
//					shortfall = money-salary;
//					player.plusDebit(shortfall);
//					player.setMoney(0);
				}
		//【パターン３   資金<給料配布額  &&	  カードが途中でなくなる】
				if((!only_facility)) {
					shortfall = salary-money;
					System.out.println("資金が足りません。" + shortfall + "ドル分のカードを売ってください");
					List<Integer> pricelist = new ArrayList<>();/*【単なるint】		売るカードの単なる価格が入ってるリスト*/
					boolean rightChoice = true;
					int chechTSP = 0;
					while (totalsellCardprice < shortfall) {/*給料を払えるまでループ。totalsellCardprice＝売りに出したカードのトータルの値段*/
						   /* 売るカードの価格        < 残りの支払い分*/
						M.moneyNotEnough(shortfall - totalsellCardprice);/*初回ループ時は【totalsellCardprice=0】なので、単純に「【shortfall】分払え」と出る*/
						M.playerBoard(player);
						int num = P.SelectFromPlayerBoard(player, board);/*プレイヤーボードからカードを選択*/
						if(player.getBoardOfPlayer().getCards().get(num).getType().equals("F")) {
							System.out.println("施設カードは売れません。もう一度選択してください");continue;
						}
						Card sellCard/*『売りに選択したカード』*/ = player.getBoardOfPlayer().getCards().remove(num);
						pricelist.add(sellCard.getPrice());/*カードの価格なので。【pricelist】＝単なるint*/
						totalsellCardprice += sellCard.getPrice();/*売るカードの【価格】をtotalsellCardpriceに足してってる*/
						TemporaryCards.add(sellCard); /*売ったカードをTemporaryCardsに仮に入れてる*/
						only_facility = true;
						for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {/*プレイヤーボード全てのカードをループで参照してる*/
							String type = player.getBoardOfPlayer().getCards().get(i).getType();
							if (!type.equals("F")) {/*プレイヤーボードに非施設が１枚でもあれば*/
								only_facility = false;
							}
						}
						if (player.getBoardOfPlayer().getCards().size() == 0) {/*プレイヤーボードが空の場合*/
							board_empty = true;
						}
//						『カードを売ったら空or施設だけになり　かつ、資金も足りないとき時』
						if(totalsellCardprice + money < salary && (only_facility || board_empty)) {
							player.plusMoney(totalsellCardprice);
							board.getHousehold().plusMoney(player.getMoney());
							player.plusMoney(-salary);
							System.out.println("パターン3");

							System.out.println(sellCard.getName()+"を売りました");
							System.out.println("家計に"+player.getMoney()+"支払います");
							System.out.println("売れるカードがありません。支払えなかった分、 "+player.getDebit()+" 借金が増えます");
							break;
						}

//		【パターン4 カードを売れば間に合う場合】
//						【単なるInteger型のpricelist】を降べきの順に並び変えてる
						Collections.sort(pricelist, Collections.reverseOrder());
//						<---⑥過剰転売を防ぐため、売ったカード枚数分forで回して調べてる---->
						for (int i = 0; i < TemporaryCards.size(); i++) {
							chechTSP += pricelist.get(i);
//							【売るカードの値段>支払うべき金額】	かつ	【3枚選択したのに2枚目で支払うべき金額を超えた】	場合に過剰転売になる。
//							4,4,8とやっても8,4,4になるように【pricelist】はCollection.sort()で降べきの順にしてある。	このforループで過剰転売の計算がしやすいように
							if(chechTSP > shortfall && i < TemporaryCards.size()-1) {
								System.out.println("過剰転売しています。やり直してください。");
//								//ここで上書きをしている。もし、
//								player.getBoardOfPlayer().setCards(new ArrayList<>(copyPlayerCards));
//								//ではなく
//								//player.getBoardOfPlayer().setCards(copyPlayerCards);
//								//とすると、保存しておいたリストの参照を単に渡すことになる
//								//つまり、次のループで建物をremoveする処理をしたときに、保存しておいたやつまで一緒に書き換えられる事になってしまう
//								//だからとりあえずの解決策としては保存しておいたやつのコピー（参照が別）を作って渡す
								player.getBoardOfPlayer().setCards(new ArrayList<>(copyPlayerCards));
								System.out.println(player.getBoardOfPlayer().getCards());/*単にプレイヤーボード一覧*/
								System.out.println(player.getBoardOfPlayer().getCards().size());/*単にプレイヤーボードの数*/
								TemporaryCards.clear();
								totalsellCardprice=0;
								//ダメなパターンだからfalse
								rightChoice=false;
								break;
							}
							//ここまで来るのは正しいパターンだからtrue
							rightChoice=true;
						}
						if(rightChoice) {
							System.out.println("パターン4");

							board.plusCardsSet(TemporaryCards);/*売ったカードをボードに建設してる*/
							player.plusMoney(totalsellCardprice);
							player.plusMoney(-salary);
							board.getHousehold().plusMoney(salary);/*salary分を家計にプラスしてる*/
							break;
						}
					}
				}
			}
		}





//----------------



//		【資金>給料】の場合は一気にここまでくる
//		家計へのお金のプラスがないのでは？
		System.out.println("---------");
		System.out.println("【"+turn+"ターン終了時】");
		/*【プレイヤーのフィールド】*/System.out.println("労働者 "+player.getWokers());
		/*【プレイヤーのフィールド】*/System.out.println("資金 "+player.getMoney());
		/*【ボードのフィールド】*/System.out.println("家計 "+board.getHousehold().getMoney());;
		/*【プレイヤーのフィールド】*/System.out.println("借金 "+player.getDebit());
		/*【プレイヤーのフィールド】*/System.out.println("土地資産 "+player.BoardOfPlayerTotalPrice());
		/*【プレイヤーのフィールド】*/System.out.println("ボーナス "+player.getBonus());
		player.setScore();
		System.out.println("スコア "+player.getScore());
		System.out.println("---------");
		System.out.println("");
	}
}
