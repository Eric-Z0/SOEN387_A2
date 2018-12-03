package org.dom.model.game;

import java.util.List;

import org.dom.model.bench.Bench;
import org.dsrg.soenea.domain.interf.IDomainObject;

//IPlay interface only provides methods to access play attributes
public interface IGame extends IDomainObject<Long> {
	
	// Methods for player one
	public abstract long getP1ID();
	
	public abstract long getP1DeckID();
	
	public abstract String getP1Status();
	
	public abstract void setP1Status(String p1Status);
	
	public abstract int getP1EPS();
	
	public abstract void setP1EPS(int p1eps);
	
	public abstract List<Long> getP1Hand();
	
	public abstract int getP1Handsize();
	
	public abstract void addCardToP1Hand(long cardID);
	
	public abstract int getP1DeckIndex();
	
	public abstract int getP1Decksize();
	
	public abstract List<Bench> getP1Bench();
	
	public abstract void addCardToP1Bench(Bench benObj, long cardID);
	
	public abstract int getP1Discardsize();
	
	public abstract List<Long> getP1DiscardPile();
	
	public abstract void playTrainerP1(long cardID);
	
	
	// Methods for player two
	public abstract long getP2ID();
	
	public abstract long getP2DeckID();
	
	public abstract String getP2Status();
	
	public abstract void setP2Status(String p2Status);
	
	public abstract int getP2EPS();
	
	public abstract void setP2EPS(int p2eps);
	
	public abstract List<Long> getP2Hand();
	
	public abstract int getP2Handsize();
	
	public abstract void addCardToP2Hand(long cardID);
	
	public abstract int getP2DeckIndex();
	
	public abstract int getP2Decksize();
	
	public abstract List<Bench> getP2Bench();
	
	public abstract void addCardToP2Bench(Bench benObj, long cardID);
	
	public abstract int getP2Discardsize();
	
	public abstract List<Long> getP2DiscardPile();
	
	public abstract void playTrainerP2(long cardID);
	
	
	// Methods for game
	public abstract int getTurn();
	
	public abstract void setTurn(int turn);
	
	public abstract long[] getPlayerIDs();
	
	public abstract long[] getDeckIDs();
	
	public abstract long getCurrentPlayerID();
	
	public abstract void setCurrentPlayerID(long currentPlayerID);
	
	public abstract boolean isPlayerInGame(long playerID);
	
	public abstract boolean isCardInPlayerHand(long playerID, long cardID);
	
	public abstract boolean isPokemonInBench(long playerID, long cardID);
	
	public abstract boolean checkPlayerPlayedEnergy(long playerID);
	
	public abstract void cleanEnergyPlayRecord();
	
	public abstract void checkPlayerHandSizeLimit(long playerID);
	
	public abstract void endPlayerTurn();
	
	public abstract long getPlayerDeckID(long playerID);
	
	public abstract int getPlayerCurrentDeckIndex();
	
	public abstract void addCardToPlayerHand(long cardID);

}
