package org.dom.model.game;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dom.model.bench.Bench;
import org.dom.model.game.mappers.GameInputMapper;


public class GameProxy extends DomainObjectProxy<Long, Game> implements IGame {
	
	public GameProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Game getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return GameInputMapper.find(id);
		} catch (MapperException | SQLException e) {
			throw new DomainObjectCreationException(e.getMessage(), e);
		}
	}
	
	// Getters and Setters for player 1
	@Override
	public long getP1ID() {
		return getInnerObject().getP1ID();
	}
	
	@Override
	public long getP1DeckID() {
		return getInnerObject().getP1DeckID();
	}
	
	@Override
	public String getP1Status() {
		return getInnerObject().getP1Status();
	}
	
	@Override
	public void setP1Status(String p1Status) {
		getInnerObject().setP1Status(p1Status);
	}
	
	@Override
	public int getP1EPS() {
		return getInnerObject().getP1EPS();
	}
	
	@Override
	public void setP1EPS(int p1eps) {
		getInnerObject().setP1EPS(p1eps);
	}
	
	@Override
	public List<Long> getP1Hand() {
		return getInnerObject().getP1Hand();
	}
	
	@Override
	public int getP1Handsize() {
		return getInnerObject().getP1Handsize();
	}
	
	@Override
	public void addCardToP1Hand(long cardID) {
		getInnerObject().addCardToP1Hand(cardID);
	}
	
	@Override
	public int getP1DeckIndex() {
		return getInnerObject().getP1DeckIndex();
	}
	
	@Override
	public int getP1Decksize() {
		return getInnerObject().getP1Decksize();
	}
	
	@Override
	public int getP1Discardsize() {
		return getInnerObject().getP1Discardsize();
	}
	
	@Override
	public List<Long> getP1DiscardPile() {
		return getInnerObject().getP1DiscardPile();
	}

	@Override
	public List<Bench> getP1Bench() {
		return getInnerObject().getP1Bench();
	}
	
	@Override
	public void addCardToP1Bench(Bench benObj, long cardID) {
		getInnerObject().addCardToP1Bench(benObj, cardID);
	}
	
	@Override
	public void playTrainerP1(long cardID) {
		getInnerObject().playTrainerP1(cardID);
	}
	
	// Getters and Setters for player 2
	@Override
	public long getP2ID() {
		return getInnerObject().getP2ID();
	}
	
	@Override
	public long getP2DeckID() {
		return getInnerObject().getP2DeckID();
	}
		
	@Override
	public String getP2Status() {
		return getInnerObject().getP2Status();
	}
		
	@Override
	public void setP2Status(String p2Status) {
		getInnerObject().setP2Status(p2Status);
	}
	
	@Override
	public int getP2EPS() {
		return getInnerObject().getP2EPS();
	}
	
	@Override
	public void setP2EPS(int p2eps) {
		getInnerObject().setP2EPS(p2eps);
	}
		
	@Override
	public List<Long> getP2Hand() {
		return getInnerObject().getP2Hand();
	}
	
	@Override
	public int getP2Handsize() {
		return getInnerObject().getP2Handsize();
	}
		
	@Override
	public void addCardToP2Hand(long cardID) {
		getInnerObject().addCardToP2Hand(cardID);
	}
	
	@Override
	public int getP2DeckIndex() {
		return getInnerObject().getP2DeckIndex();
	}
		
	@Override
	public int getP2Decksize() {
		return getInnerObject().getP2Decksize();
	}
		
	@Override
	public int getP2Discardsize() {
		return getInnerObject().getP2Discardsize();
	}
	
	@Override
	public List<Long> getP2DiscardPile() {
		return getInnerObject().getP2DiscardPile();
	}

	@Override
	public List<Bench> getP2Bench() {
		return getInnerObject().getP2Bench();
	}
		
	@Override
	public void addCardToP2Bench(Bench benObj, long cardID) {
		getInnerObject().addCardToP2Bench(benObj, cardID);
	}
	
	@Override
	public void playTrainerP2(long cardID) {
		getInnerObject().playTrainerP2(cardID);
	}

	// Method for game
	@Override
	public int getTurn() {
		return getInnerObject().getTurn();
	}
	
	@Override
	public void setTurn(int turn) {
		getInnerObject().setTurn(turn);
	}
	
	@Override
	public long[] getPlayerIDs() {
		return getInnerObject().getPlayerIDs();
	}

	@Override
	public long[] getDeckIDs() {
		return getInnerObject().getDeckIDs();
	}

	@Override
	public long getCurrentPlayerID() {
		return getInnerObject().getCurrentPlayerID();
	}

	@Override
	public void setCurrentPlayerID(long currentPlayerID) {
		getInnerObject().setCurrentPlayerID(currentPlayerID);
	}
	
	@Override
	public boolean isPlayerInGame(long playerID) {
		return getInnerObject().isPlayerInGame(playerID);
	}
	
	@Override
	public boolean isCardInPlayerHand(long playerID, long cardID) {
		return getInnerObject().isCardInPlayerHand(playerID, cardID);
	}
	
	@Override
	public boolean isPokemonInBench(long playerID, long cardID) {
		return getInnerObject().isPokemonInBench(playerID, cardID);
	}
	
	@Override
	public boolean checkPlayerPlayedEnergy(long playerID) {
		return getInnerObject().checkPlayerPlayedEnergy(playerID);
	}
	
	@Override
	public void cleanEnergyPlayRecord() {
		getInnerObject().cleanEnergyPlayRecord();
	}

	@Override
	public void checkPlayerHandSizeLimit(long playerID) {
		getInnerObject().checkPlayerHandSizeLimit(playerID);
	}

	@Override
	public void endPlayerTurn() {
		getInnerObject().endPlayerTurn();
	}

	@Override
	public long getPlayerDeckID(long playerID) {
		return getInnerObject().getPlayerDeckID(playerID);
	}

	@Override
	public int getPlayerCurrentDeckIndex() {
		return getInnerObject().getPlayerCurrentDeckIndex();
	}

	@Override
	public void addCardToPlayerHand(long cardID) {
		getInnerObject().addCardToPlayerHand(cardID);
	}

}
