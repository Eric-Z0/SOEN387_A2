package org.ser;


import org.app.PokeFC;
import org.dom.model.bench.tdg.BenchTDG;
import org.dom.model.card.tdg.CardTDG;
import org.dom.model.challenge.tdg.ChallengeTDG;
import org.dom.model.deck.tdg.DeckTDG;
import org.dom.model.game.tdg.GameTDG;
import org.dom.model.user.tdg.UserTDG;


public class RenewDatabase {

	public static void main(String[] args) {
		
		PokeFC.prepareDbRegistry("");
		
		try {
			UserTDG.dropTable();
			CardTDG.dropTable();
			DeckTDG.dropTable();
			BenchTDG.dropTable();
			GameTDG.dropTable();
			ChallengeTDG.dropTable();
		} catch(Exception e){}
		
		
		try {
			UserTDG.createTable();
			CardTDG.createTable();
			DeckTDG.createTable();
			BenchTDG.createTable();
			GameTDG.createTable();
			ChallengeTDG.createTable();
		} catch(Exception e){}

	}

}
