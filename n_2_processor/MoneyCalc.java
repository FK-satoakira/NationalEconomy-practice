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
		List<Card> originalPlayerCards = new ArrayList<>(player.getBoardOfPlayer().getCards());/*プレイヤーボードのカード一覧をoriginalPlayerCardsに渡してる*/

		//List<Card> pbc = player.getBoardOfPlayer().getCards();
//		フィールド
		int salary = 0;/*支払金額*/
		int Shortfall = 0;/*残高*/
		int money = player.getMoney();/*資金*/
		int totalsellCardprice = 0;/*売るカードの値段*/
		int perPerson = 0;/*そのターンの一人当たり給料*/
//		boolean can_sell_land = true; /*売れる土地があるか否かのboolean*/

		if(turn<3) {
			perPerson = 2;
		}else if (turn < 6) {
			perPerson = 3;
		}else if (turn < 8) {
			perPerson = 4;
		}else {
			perPerson = 5;
		}
		System.out.println("資金 " + player.getMoney());/*今持ってるお金*/
		salary = player.getWokers().size()*perPerson;/*支払うべき金額。		wokerについてはplayerのフィールドを参照*/










//		<-----②【【資金 < 給料支払額】	金欠で、プレイヤーボードからカードを売る必要がある場合のif文】はここから----->
		if (money < salary) {
			Shortfall = salary - money;/*【支払いに足りない分】	Shortfall=残高。*/
			boolean rightChoice = true;/*このbooleanは05ループから抜け出すためのもの。132行目あたりまで出てこない*/
//			<---05売れなくなるまでループ--->
			while (true) {
//				<-----01【売るカードがプレイヤーボードにない場合】のコードはここから----->
				if (player.getBoardOfPlayer().getCards().size() == 0) {/*プレイヤーボードが空の場合*/
//					System.out.println("size0 break");
					break;
				}
				int count = 0;/*【プレイヤーボードに施設カード以外のカードがあった場合、この【count】はプラスされる】*/
				for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {/*プレイヤーボード全てのカードをループで参照してる*/
					String type = player.getBoardOfPlayer().getCards().get(i).getType();
					if(!type.equals("F")) {
						count++; /*施設カードでないならcount++*/
					}
				}
				if (count == 0) {
					break;		/*プレイヤーボードが施設カードだけならループから抜ける*/
				}
//				<-----01ここまで。----->

//				【例】moeny=2、salary=10、ならShortfall=8。

				List<Integer> pricelist = new ArrayList<>();/*【単なるint】		売るカードの単なる価格が入ってるリスト*/

//				<----③【資金がない】かつ【売るカードはある】場合のwhile文---->
				while (totalsellCardprice < Shortfall) {/*給料を払えるまでループ。totalsellCardprice＝売りに出したカードのトータルの値段*/
					   /* 売るカードの価格        < 残りの支払い分*/
					M.moneyNotEnough(Shortfall - totalsellCardprice);/*初回ループ時は【totalsellCardprice=0】なので、単純に「【Shortfall】分払え」と出る*/
					M.playerBoard(player);
					int num = P.SelectFromPlayerBoard(player, board);/*プレイヤーボードからカードを選択*/
					Card sellCard/*【売りに選択したカード】*/ = player.getBoardOfPlayer().getCards().remove(num);
					pricelist.add(sellCard.getPrice());/*カードの価格なので。【pricelist】＝単なるint*/
//					Collections.sort(pricelist, Collections.reverseOrder());/*価格の高い順に並び変えてる*/
		/*int型*/	totalsellCardprice += sellCard.getPrice();/*売るカードの【価格】をtotalsellCardpriceに足してってる*/
					TemporaryCards.add(sellCard); /*売ったカードをTemporaryCardsに仮に入れてる*/

					if (player.getBoardOfPlayer().getCards().size() == 0) {/*カードを売ってプレイヤーボードが空になった場合にwhileループから出る*/
						break;
					}
					int count2 = 0;
//					<---④カードを売ってプレイヤーボードが施設だけになった場合にwhileループから出る--->
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
//					<---④--->
				}
//				<----③---->

//				【単なるInteger型のpricelist】を降べきの順に並び変えてる
				Collections.sort(pricelist, Collections.reverseOrder());
				int chechTSP = 0;

//				<---⑥過剰転売を防ぐため、売ったカード枚数分forで回して調べてる---->
				for (int i = 0; i < TemporaryCards.size(); i++) {
					chechTSP += pricelist.get(i);
//					【売るカードの値段>支払うべき金額】	かつ	【3枚選択したのに2枚目で支払うべき金額を超えた】	場合に過剰転売になる。
//					4,4,8とやっても8,4,4になるように【pricelist】はCollection.sort()で降べきの順にしてある。	このforループで過剰転売の計算がしやすいように
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
//				<---⑥--->

				//正しいパターンのときのみ抜けられる
				if(rightChoice) {
					board.plusCardsSet(TemporaryCards);/*売ったカードをボードに建設してる*/
					//必ずしもsalary分家計が増えるわけではない。なぜなら、土地も金もなければ家計は増えずに謝金だけが増える
					board.getHousehold().plusMoney(salary);/*salary分を家計にプラスしてる*/
					player.plusMoney(totalsellCardprice);
					break;
				}
			}
//			<---⑤--->
//			①は一気にここまでくる
		}
//		<-----②ここまで----->

//		【資金>給料】の場合は一気にここまでくる
//		家計へのお金のプラスがないのでは？
		System.out.println("---------");
		System.out.println("【"+turn+"ターン終了時】");
		System.out.println(salary+"ドルの給料を払います");
		player.plusMoney(-salary);/*自分のお金から労働者へ給料を払っている*/
		/*【プレイヤーのフィールド】*/System.out.println("資金 "+player.getMoney());
		/*【ボードのフィールド】*/System.out.println("家計 "+board.getHousehold().getMoney());;
		/*【プレイヤーのフィールド】*/System.out.println("借金 "+player.getDebit());
		/*【プレイヤーのフィールド】*/System.out.println("土地資産 "+player.BoardOfPlayerTotalPrice());
		/*【プレイヤーのフィールド】*/System.out.println("ボーナス "+player.getBonus());
		player.setScore();
		System.out.println("スコア "+player.getScore());
		System.out.println("---------");

	}
}

//【支払の4パターン】を考えたほうがいいのでは？
//１、払える
//払えない--------2、売れば払える
//		------3、売っても足りない
//		------4、もとから売れない

