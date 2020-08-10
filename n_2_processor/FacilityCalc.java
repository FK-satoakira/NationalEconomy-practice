package n_2_processor;

import java.util.List;

import n_2_board.Player;
import n_2_card.Card;

public class FacilityCalc {

	public FacilityCalc() {
	}
	public void facilityCalc(Player player) {
		player.clearBonusRealEstate();
		List<Card> pbc = player.getBoardOfPlayer().getCards();
		for (int i = 0; i < pbc.size(); i++) {
			Card facility = pbc.get(i);
			switch(facility.getName()) {
				case "倉庫":
					if(facility.isOn()) {
						player.plusHandlimit(4);facility.setOn(false);
					}
					break;
				case "社宅":
					if(facility.isOn()) {
						player.plusWokerlimit(1);facility.setOn(false);
					}
					break;
				case "不動産":
//					if(facility.isOn()) {
						player.plusBonusRealEstate(pbc.size()*3);
//					}
					break;
				case "労働組合":
						player.setBonusUnion(player.getWokers().size()*6);
					break;
				case "本社ビル":
					int count = 0;
					for (int j = 0; j < pbc.size(); j++) {
						if(pbc.get(j).getType().equals("F")) {
							count++;
						}
					}
					player.setBonusHeadOfficeBuilding(count*6);
					break;
				case "鉄道":
					int countT = 0;
					for (int j = 0; j < pbc.size(); j++) {
						if(pbc.get(j).getType().equals("I")) {
							countT++;
						}
					}
					player.setBonusTrain(countT*8);
					break;
				case "農協":
					int countC = 0;
					for (int j = 0; j < player.getCards().size(); j++) {
						if(player.getCards().get(j).getType().equals("0")) {
							countC++;
						}
					}
					player.setBonusAgriculturalCooperative(countC*3);
					break;
				case "法律事務所":
					if(player.getDebit()<=15) {

						player.plusDebit(-(player.getDebit()/3));
					}else {
						player.plusDebit(-5);
					}
					break;
			}
		}
	}
}
