package n_2_card;

import n_2_board.Board;
import n_2_board.Player;
import n_2_processor.M;
import n_2_processor.P;

public abstract class Card {
	protected String name = "";
	private int price = 0;
	private int trash = 0;
	private String type = "";
	private boolean on  = true;

	public Card(String name, int price, int trash, String type) {
		this.name=name;this.price=price;this.trash=trash;this.type=type;
//		switch(type) {
//			case 0:
//				this.type="P";
//				break;
//			case 1:
//				this.type="F";
//				break;
//			case 2:
//				this.type="A";
//				break;
//			case 3:
//				this.type="I";
//				break;
//			case 4:
//				this.type="n";
//				break;
//			default:
//				this.type="0";
//		}

	}
	public boolean ability(Player player, Board board, P processor) {
		if(!this.isOn()) {M.Choicenot();return true;}
		boolean useble = secondAbility(player, board, processor);
		if(!useble) {this.setOn(false);}
		return useble;
	}
	protected abstract boolean secondAbility(Player player, Board board, P processor);
//	名前
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	価格
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
//	捨て札数
	public int getTrash() {
		return trash;
	}
	public void setTrash(int trash) {
		this.trash = trash;
	}
//	属性
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	@Override
	public String toString() {
		return name + " " + price + " " + trash + " " + type;
	}


}
