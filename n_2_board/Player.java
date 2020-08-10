package n_2_board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import n_2_card.C_A_Orchard;
import n_2_card.C_A_Pioneer;
import n_2_card.C_I_Large_CarFactory;
import n_2_card.Card;
import n_2_processor.P;

public class Player {
	private String name = "";
	private int money = 5;
	private int wokerlimit = 5;
	private int handlimit = 5;
	private int debit = 0;
	private List<Woker> wokers = new ArrayList<>();
	private List<Card> cards = new ArrayList<>();
	private BoardOfPlayer boardOfPlayer = new BoardOfPlayer();
//	施設カードのボーナス
	private int bonusRealEstate = 0;
	private int bonusHeadOfficeBuilding = 0;
	private int bonusTrain = 0;
	private int bonusUnion = 0;
	private int bonusAgriculturalCooperative = 0;
	private int bonus = bonusRealEstate+bonusHeadOfficeBuilding+bonusTrain+bonusUnion;
//	スコア
	private int score = 0;

	public Player(Board board) {
//		手札
		for (int i = 0; i < 3; i++) {
			int r = new Random().nextInt(board.getDeck().getCards().size());
			this.plusCards(board.getDeck().getCards().get(r));}
		this.plusCards(new C_A_Pioneer());
		this.plusCards(new C_A_Orchard());
		this.plusCards(new C_I_Large_CarFactory());
//		this.plusCards(new C_I_DesignOffice());
//		this.plusCards(new C_I_DesignOffice());

//		プレイヤーフィールド
		for (int i = 0; i < 2; i++) {this.boardOfPlayer.getCards().add(new C_I_Large_CarFactory());}
//		労働者
		this.wokers.add(new Woker());
		this.wokers.add(new Woker());
//		this.wokers.add(new Woker());
//		this.wokers.add(new Woker());
//		this.wokers.add(new Woker());
	}

//	メソッド
	public boolean useboard(Board board, P processor) {
		board.useCard(this, processor);
		return false;
	}
//労働者
	public List<Woker> getWokers() {
		return wokers;
	}
	public void plusWokers(Woker woker) {
		this.wokers.add(woker);
	}
	public void setWokers(List<Woker> wokers) {
		this.wokers = wokers;
	}
	public void setWokers(int num) {
		while(num > wokers.size()) {
			this.wokers.add(new Woker());
		}
	}
//労働者制限
	public int getWokerlimit() {
		return wokerlimit;
	}
	public void plusWokerlimit(int wokerlimit) {
		this.wokerlimit += wokerlimit;
	}
	public void setWokerlimit(int wokerlimit) {
		this.wokerlimit = wokerlimit;
	}
//プレイヤー名
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//資金
	public int getMoney() {
		if(money<0) {
			this.plusDebit(money);
			this.money=0;
		}
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void plusMoney(int money) {
		this.money += money;
	}
//	借金
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit*3;
	}
	public void plusDebit(int debit) {
		this.debit += debit*3;
	}
	public void clearDebit() {
		this.debit = 0;
	}

//	手札
	public List<Card> getCards() {
		return cards;
	}
	public void plusCards(Card card) {
		this.cards.add(card);
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
//手札制限
	public int getHandlimit() {
		return handlimit;
	}

	public void plusHandlimit(int handlimit) {
		this.handlimit += handlimit;
	}
//プレイヤーフィールド
	public BoardOfPlayer getBoardOfPlayer() {
		return boardOfPlayer;
	}
	public void plusBoardOfPlayer(Card card) {
		this.boardOfPlayer.getCards().add(card);
	}
	public void setBoardOfPlayer(BoardOfPlayer boardOfPlayer) {
		this.boardOfPlayer = boardOfPlayer;
	}
	public int BoardOfPlayerTotalPrice() {
		int totalPrice = 0;
		for (int i = 0; i < boardOfPlayer.getCards().size(); i++) {
			totalPrice += boardOfPlayer.getCards().get(i).getPrice();
		}
		return totalPrice;
	}
	//不動産ボーナス
	public int getBonusRealEstate() {
		return bonusRealEstate;
	}
	public void setBonusRealEstate(int realEstate) {
		bonusRealEstate = realEstate;
	}
	public void plusBonusRealEstate(int realEstate) {
		bonusRealEstate += realEstate;
	}
	public void clearBonusRealEstate() {
		bonusRealEstate = 0;
	}
//	----
//本社ビルボーナス
	public int getBonusHeadOfficeBuilding() {
		return bonusHeadOfficeBuilding;
	}
	public void setBonusHeadOfficeBuilding(int bonusHeadOfficeBuilding) {
		this.bonusHeadOfficeBuilding = bonusHeadOfficeBuilding;
	}
	public void clearBonusHeadOfficeBuilding() {
		bonusHeadOfficeBuilding = 0;
	}
//鉄道ボーナス
	public int getBonusTrain() {
		return bonusTrain;
	}
	public void setBonusTrain(int bonusTrain) {
		this.bonusTrain = bonusTrain;
	}
	public void clearBonusTrain() {
		bonusTrain = 0;
	}
//労働組合ボーナス
	public int getBonusUnion() {
		return bonusUnion;
	}
	public void setBonusUnion(int bonusUnion) {
		this.bonusUnion = bonusUnion;
	}
	public void clearBonusUnion() {
		bonusUnion = 0;
	}
//	農協ボーナス
	public int getBonusAgriculturalCooperative() {
		return bonusAgriculturalCooperative;
	}
	public void setBonusAgriculturalCooperative(int bonusAgriculturalCooperative) {
		this.bonusAgriculturalCooperative = bonusAgriculturalCooperative;
	}
//ボーナス
	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
//スコア
	public int getScore() {
		return score;
	}

	public void setScore() {
		this.score = this.money+this.bonus+this.BoardOfPlayerTotalPrice()-this.debit;
	}

	@Override
	public String toString() {
		return ""+ cards;
	}

}
