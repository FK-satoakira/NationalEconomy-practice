package n_2_board;

public class Household {
	private int money = 0;
	public Household() {
	}
	public int getMoney() {
		return money;
	}
	public int plusMoney(int money) {
		this.money += money;
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int minusMoney(int money) {
		this.money -= money;
		return money;
	}
}
