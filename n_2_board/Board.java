package n_2_board;

import java.util.ArrayList;
import java.util.List;

import n_2_card.C_A_Farm;
import n_2_card.C_A_LargeFarm;
import n_2_card.C_A_Orchard;
import n_2_card.C_A_SlashAndBurn;
import n_2_card.C_B_ErhuConstruction;
import n_2_card.C_B_GeneralContractor;
import n_2_card.C_B_constructionCompany;
import n_2_card.C_F_CoffeeShop;
import n_2_card.C_F_Restaurant;
import n_2_card.C_Faci_AgriculturalCooperative;
import n_2_card.C_Faci_CompanyHousing;
import n_2_card.C_Faci_HeadOfficeBuilding;
import n_2_card.C_Faci_LawOffice;
import n_2_card.C_Faci_Mansion;
import n_2_card.C_Faci_RealEstate;
import n_2_card.C_Faci_Train;
import n_2_card.C_Faci_Union;
import n_2_card.C_Faci_WareHouse;
import n_2_card.C_I_CarFactory;
import n_2_card.C_I_ChemicalFactory;
import n_2_card.C_I_Factory;
import n_2_card.C_I_SteelMill;
import n_2_card.C_P_Carpenter;
import n_2_card.C_P_Mine;
import n_2_card.C_P_School;
import n_2_card.C_P_Vocational;
import n_2_card.Card;
import n_2_processor.M;
import n_2_processor.P;

public class Board {

	private List<Card> cards = new ArrayList<>();
	private Trash trashCard = new Trash();
	private Deck deck = new Deck();
	private Household household = new Household();

	public Board() {
		// ボード
		this.cards.add(new C_P_Mine());
		this.cards.add(new C_P_Carpenter());
		this.cards.add(new C_P_School());
		this.cards.add(new C_A_Farm());
		this.cards.add(new C_P_Vocational());
		// this.cards.add(new C_I_SteelMill());

		this.deck.getCards().add(new C_A_SlashAndBurn());
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_A_Farm());
		}
		for (int i = 0; i < 4; i++) {
			this.deck.getCards().add(new C_A_Orchard());
		}
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_A_LargeFarm());
		}

		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_B_constructionCompany());
		}
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_B_GeneralContractor());
		}
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_B_ErhuConstruction());
		}

		this.deck.getCards().add(new C_F_CoffeeShop());
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_F_Restaurant());
		}

		for (int i = 0; i < 6; i++) {
			this.deck.getCards().add(new C_I_Factory());
		}
		for (int i = 0; i < 4; i++) {
			this.deck.getCards().add(new C_I_ChemicalFactory());
		}
		for (int i = 0; i < 4; i++) {
			this.deck.getCards().add(new C_I_SteelMill());
		}
		for (int i = 0; i < 4; i++) {
			this.deck.getCards().add(new C_I_CarFactory());
		}

		this.deck.getCards().add(new C_Faci_AgriculturalCooperative());
		this.deck.getCards().add(new C_Faci_HeadOfficeBuilding());
		this.deck.getCards().add(new C_Faci_LawOffice());
		this.deck.getCards().add(new C_Faci_Mansion());
		this.deck.getCards().add(new C_Faci_Union());
		this.deck.getCards().add(new C_Faci_Train());
		this.deck.getCards().add(new C_Faci_LawOffice());
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_Faci_CompanyHousing());
		}
		for (int i = 0; i < 2; i++) {
			this.deck.getCards().add(new C_Faci_RealEstate());
		}
		for (int i = 0; i < 4; i++) {
			this.deck.getCards().add(new C_Faci_WareHouse());
		}
		// for (int i = 0; i < 2; i++) {this.deck.getCards().add(new ());}
		// for (int i = 0; i < 2; i++) {this.deck.getCards().add(new ());}
		// for (int i = 0; i < 2; i++) {this.deck.getCards().add(new ());}
		// for (int i = 0; i < 2; i++) {this.deck.getCards().add(new ());}
		// for (int i = 0; i < 2; i++) {this.deck.getCards().add(new ());}
		// for (int i = 0; i < 4; i++) {this.deck.getCards().add(new ());}

		// this.deck.getCards().add(new ());

		// 家計
		// this.household.setMoney(40);
	}

	// カードのabilityを使う
	public void useCard(Player player, P processor) {
		// ボードかプレイヤーボードかを選択
		boolean onroop = true;
		while (onroop) {
			// try {
			M.playerCard(player);
			// ［ボード］を使用する場合は[1]、
			// ［マイボード］を使用する場合は[2]を選択してください
			// M.Board_Or_PlayerBoard();
			M.board(this);
			M.playerBoard(player);
			int numberB_or_PB = P.GameBoard_Or_PlayerBoard(player, this);

			// switch文に慣れていないので、メモする。bｒeakが無いと無限ループ
			switch (numberB_or_PB) {
				case 0:
					M.CardChoice();
					M.board(this);
					int boardCard = P.SelectFromBoard(player, this);
					// boardCard=(数値)のボードカードを使用している
					onroop = this.cards.get(boardCard).ability(player, this, processor);
					// System.out.println(onroop);
					break;
				case 1:
					if (player.getBoardOfPlayer().getCards().size() == 0) {
						System.out.println("プレイヤーボードは空です");
						M.retry();
						continue;
					}
					int count = 0;
					for (int i = 0; i < player.getBoardOfPlayer().getCards().size(); i++) {
						String type = player.getBoardOfPlayer().getCards().get(i).getType();
						if (!type.equals("F")) {
							count++;
						}
					}
					if (count == 0) {
						System.out.println("プレイヤーボードは施設カードしかありません");
						continue;
					}
					M.CardChoice();
					M.playerBoard(player);
					int playerBoardCard = P.SelectFromPlayerBoard(player, this);
					// playerBoardCard=(数値)のボードカードを使用している
					onroop = player.getBoardOfPlayer().getCards().get(playerBoardCard).ability(player, this, processor);
					break;
				default:
					System.out.println("もう一度やり直してくださいaaa");
					continue;
			}
			// } catch (Exception e) {
			// M.DifferentType(e);
			// continue;
			// }
		}

	}

	// ボード
	public List<Card> getCards() {
		return cards;
	}

	public void plusCards(Card card) {
		this.cards.add(card);
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void plusCardsSet(List<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {

			this.cards.add(cards.get(i));
		}
	}

	// トラッシュ
	public Trash getTrashCard() {
		return trashCard;
	}

	public void plusTrashCard(Card card) {
		this.trashCard.getCards().add(card);
	}

	public void setTrashCard(Trash trashCard) {
		this.trashCard = trashCard;
	}

	// デッキが空になったらトラッシュから新たにデッキを作る
	public Deck getDeck() {
		// if(this.deck.getCards().size()==0) {
		if (this.deck.getCards().isEmpty()) {
			System.out.println(this.deck.getCards().size());
			int num = this.trashCard.getCards().size();
			for (int i = 0; i < num; i++) {
				// System.out.println(this.deck.getCards().get(0).getName());
				this.deck.plusCards(this.trashCard.getCards().get(0));
			}

		}
		return deck;
	}

	public void plusDeck(Card card) {
		this.deck.getCards().add(card);
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	@Override
	public String toString() {
		return "ボード: " + cards;
	}

}
