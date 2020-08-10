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
		List<Card> originalPlayerCards = new ArrayList<>(player.getBoardOfPlayer().getCards());

		//List<Card> pbc = player.getBoardOfPlayer().getCards();
		int salary = 0;/*支払金額*/
		int Shortfall = 0;/*残高*/
		int money = player.getMoney();/*資金*/
		int totalsellCardprice = 0;/*売るカードの値段*/
		int perPerson = 0;
		if(turn<3) {
			perPerson = 2;
		}else if (turn < 6) {
			perPerson = 3;
		}else if (turn < 8) {
			perPerson = 4;
		}else {
			perPerson = 5;
		}
		System.out.println("資金 "+player.getMoney());
		salary = player.getWokers().size()*perPerson;/*支払金額*/
		if(money < salary) {
			Shortfall = salary - money;
			boolean rightChoice=true;

			while (true) {/*過剰転売時のループ*/
//				System.out.println("outer loop");
				if (player.getBoardOfPlayer().getCards().size() == 0) {/*プレイヤーボードが空の場合*/
//					System.out.println("size0 break");
					break;
				}
				int count = 0;   /*プレイヤーボードが施設だけの場合*/
				for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {
					String type = player.getBoardOfPlayer().getCards().get(i).getType();
					if(!type.equals("F")) {
						count++;
					}
				}
				if(count==0) {
//					System.out.println("count0 break");
					break;
				}
				List<Integer> pricelist = new ArrayList<>();/*売ったカードのpriceのリスト*/

				while (totalsellCardprice < Shortfall) {/*不足金が満たされるまでループ*/
					//全部売っても足りない場合は？
					M.moneyNotEnough(Shortfall-totalsellCardprice);
					M.playerBoard(player);
//					付け足し

					int num = P.SelectFromPlayerBoard(player, board);
					Card sellCard = player.getBoardOfPlayer().getCards().remove(num);
					pricelist.add(sellCard.getPrice());
					Collections.sort(pricelist, Collections.reverseOrder());

					totalsellCardprice += sellCard.getPrice();/*売るカードのpriceをtotalsellCardpriceに足してってる*/
					TemporaryCards.add(sellCard); /*売ったカードをTemporaryCardsに仮に入れてる*/
					if (player.getBoardOfPlayer().getCards().size() == 0) {/*プレイヤーボードが空の場合*/
//						System.out.println("size02 break");
						break;
					}
					int count2 = 0;   /*プレイヤーボードが施設だけの場合*/
					for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {
						String type = player.getBoardOfPlayer().getCards().get(i).getType();
						if(!type.equals("F")) {
							count2++;
						}
					}
					if(count2==0) {
//						System.out.println("count0 break");
						break;
					}
				}
				Collections.sort(pricelist, Collections.reverseOrder());
				int chechTSP = 0;
//				売ったカードのチェック
				for (int i = 0; i < TemporaryCards.size(); i++) {
					//たとえば、20支払わなくてはならないときに、6,6,18と売った時を考えてください
					//今のままだと許されてしまいます
//					付け足し
					chechTSP += pricelist.get(i);
					if(chechTSP > Shortfall && i < TemporaryCards.size()-1) {
						System.out.println("過剰転売しています。やり直してください。");

//						//ここで上書きをしている。もし、
//						player.getBoardOfPlayer().setCards(new ArrayList<>(originalPlayerCards));
//						//ではなく
//						//player.getBoardOfPlayer().setCards(originalPlayerCards);
//						//とすると、保存しておいたリストの参照を単に渡すことになる
//						//つまり、次のループで建物をremoveする処理をしたときに、保存しておいたやつまで一緒に書き換えられる事になってしまう
//						//だからとりあえずの解決策としては保存しておいたやつのコピー（参照が別）を作って渡す
//
						//ここで上書きをしている。もし、
						player.getBoardOfPlayer().setCards(new ArrayList<>(originalPlayerCards));
						//ではなく
						//player.getBoardOfPlayer().setCards(originalPlayerCards);
						//とすると、保存しておいたリストの参照を単に渡すことになる
						//つまり、次のループで建物をremoveする処理をしたときに、保存しておいたやつまで一緒に書き換えられる事になってしまう
						//だからとりあえずの解決策としては保存しておいたやつのコピー（参照が別）を作って渡す

						System.out.println(player.getBoardOfPlayer().getCards());
						System.out.println(player.getBoardOfPlayer().getCards().size());
						TemporaryCards.clear();
						totalsellCardprice=0;
						//ダメなパターンだからfalse
						rightChoice=false;
						break;
					}
					//ここまで来るのは正しいパターンだからtrue
					rightChoice=true;
				}
//				System.out.println("chechTSP="+chechTSP);
//				System.out.println("TemporaryCards.size()="+TemporaryCards.size());
				//正しいパターンのときのみ抜けられる
				if(rightChoice) {
					board.plusCardsSet(TemporaryCards);
					//必ずしもsalary分家計が増えるわけではないはず
					//確かそういうルールだったような。確証なし
					board.getHousehold().plusMoney(salary);
					player.plusMoney(totalsellCardprice);
					break;
				}
			}
		}
		System.out.println(salary+"ドルの給料を払います");
		player.plusMoney(-salary);
		System.out.println("資金 "+player.getMoney());
		System.out.println("家計 "+board.getHousehold().getMoney());;
		System.out.println("借金 "+player.getDebit());
		System.out.println("土地資産 "+player.BoardOfPlayerTotalPrice());
		System.out.println("ボーナス "+player.getBonus());
		player.setScore();
		System.out.println("スコア "+player.getScore());
	}
}
