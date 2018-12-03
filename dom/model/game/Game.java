package org.dom.model.game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom.model.bench.Bench;
import org.dom.model.bench.mappers.BenchInputMapper;
import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;

public class Game extends DomainObject<Long> implements IGame {
	
	private final int DEFAULT_CARD_NUM = 40;
	private int turn;
	private long currentPlayerID;
	
	private long p1ID;
	private long p1DeckID;
	private String p1Status;
	private int p1Decksize;
	private List<Long> p1Hand;
	private List<Long> p1DiscardPile;
	private List<Long> p1BenchIDList;
	private List<Bench> p1Bench;
	private int p1EPS;	// energy played status
	
	private long p2ID;
	private long p2DeckID;
	private String p2Status;
	private int p2Decksize;
	private List<Long> p2Hand;
	private List<Long> p2DiscardPile;
	private List<Long> p2BenchIDList;
	private List<Bench> p2Bench;
	private int p2EPS;	// energy played status
	
	
	protected Game(long id, long version, int turn, long CPID,
			long p1ID, long p1DeckID, String p1Status, List<Long> p1Hand, int p1Decksize, List<Long> p1DiscardPile, List<Long> p1BenchIDList, int p1EPS,
			long p2ID, long p2DeckID, String p2Status, List<Long> p2Hand, int p2Decksize, List<Long> p2DiscardPile, List<Long> p2BenchIDList, int p2EPS) {
		
		super(id, version);
		this.turn = turn;
		this.currentPlayerID = CPID;
		
		this.p1ID = p1ID;
		this.p1DeckID = p1DeckID;
		this.p1Status = p1Status;
		this.p1Hand = p1Hand;
		this.p1Decksize = p1Decksize;
		this.p1DiscardPile = p1DiscardPile;
		this.p1BenchIDList = p1BenchIDList;
		this.genP1Bench();
		this.p1EPS = p1EPS;
		
		this.p2ID = p2ID;
		this.p2DeckID = p2DeckID;
		this.p2Status = p2Status;
		this.p2Hand = p2Hand;
		this.p2Decksize = p2Decksize;
		this.p2DiscardPile = p2DiscardPile;
		this.p2BenchIDList = p2BenchIDList;
		this.genP2Bench();
		this.p1EPS = p1EPS;
	}

	// Getters and Setters for player 1
	@Override
	public long getP1ID() {
		return this.p1ID;
	}
	
	@Override
	public long getP1DeckID() {
		return this.p1DeckID;
	}
	
	@Override
	public String getP1Status() {
		return this.p1Status;
	}
	
	@Override
	public void setP1Status(String p1Status) {
		this.p1Status = p1Status;
	}
	
	@Override
	public int getP1EPS() {
		return this.p1EPS;
	}
	
	@Override
	public void setP1EPS(int p1eps) {
		this.p1EPS = p1eps;
	}
	
	@Override
	public List<Long> getP1Hand() {
		return this.p1Hand;
	}
	
	@Override
	public int getP1Handsize() {
		return this.p1Hand.size();
	}
	
	@Override
	public void addCardToP1Hand(long cardID) {
		this.p1Hand.add(cardID);
		this.p1Decksize--;
	}
	
	@Override
	public int getP1DeckIndex() {
		return (DEFAULT_CARD_NUM - this.p1Decksize);
	}
	
	@Override
	public int getP1Decksize() {
		return this.p1Decksize;
	}
	
	public void genP1Bench() {
		this.p1Bench = new ArrayList<Bench>();
		for(long benchID : this.p1BenchIDList) {
			Bench benchObj;
			try {
				benchObj = BenchInputMapper.find(benchID);
				this.p1Bench.add(benchObj);
			} catch (SQLException | MapperException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Long> getP1BenchIDList(){
		return this.p1BenchIDList;
	}
	
	@Override
	public List<Bench> getP1Bench() {
		return this.p1Bench;
	}
	
	@Override
	public void addCardToP1Bench(Bench benObj, long cardID) {
		this.p1Bench.add(benObj);
		this.p1BenchIDList.add(benObj.getId());
		this.p1Hand.remove(cardID);
		this.p1Decksize--;
	}
	
	@Override
	public int getP1Discardsize() {
		return this.p1DiscardPile.size();
	}
	
	@Override
	public List<Long> getP1DiscardPile() {
		return p1DiscardPile;
	}
	
	@Override
	public void playTrainerP1(long cardID) {
		// move the trainer card to the top of the discard pile
		this.p1DiscardPile.add(cardID);
		// removed the trainer card from player's hand
		this.p1Hand.remove(cardID);
	}

	
	// Getters and Setters for player 2
	@Override
	public long getP2ID() {
		return this.p2ID;
	}
	
	@Override
	public long getP2DeckID() {
		return this.p2DeckID;
	}
		
	@Override
	public String getP2Status() {
		return this.p2Status;
	}
		
	@Override
	public void setP2Status(String p2Status) {
		this.p2Status = p2Status;
	}
	
	@Override
	public int getP2EPS() {
		return this.p2EPS;
	}
	
	@Override
	public void setP2EPS(int p2eps) {
		this.p2EPS = p2eps;
	}
		
	@Override
	public List<Long> getP2Hand() {
		return this.p2Hand;
	}
	
	@Override
	public int getP2Handsize() {
		return this.p2Hand.size();
	}
		
	@Override
	public void addCardToP2Hand(long cardID) {
		this.p2Hand.add(cardID);
		this.p2Decksize--;
	}
	
	@Override
	public int getP2DeckIndex() {
		return (DEFAULT_CARD_NUM - this.p2Decksize);
	}
		
	@Override
	public int getP2Decksize() {
		return this.p2Decksize;
	}
	
	public void genP2Bench() {
		this.p2Bench = new ArrayList<Bench>();
		for(long benchID : this.p2BenchIDList) {
			Bench benchObj;
			try {
				benchObj = BenchInputMapper.find(benchID);
				this.p2Bench.add(benchObj);
			} catch (SQLException | MapperException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Long> getP2BenchIDList(){
		return this.p2BenchIDList;
	}

	@Override
	public List<Bench> getP2Bench() {
		return this.p2Bench;
	}
		
	@Override
	public void addCardToP2Bench(Bench benObj, long cardID) {
		this.p1Bench.add(benObj);
		this.p1BenchIDList.add(benObj.getId());
		this.p1Hand.remove(cardID);
		this.p1Decksize--;
	}
	
	@Override
	public int getP2Discardsize() {
		return this.p2DiscardPile.size();
	}
	
	@Override
	public List<Long> getP2DiscardPile() {
		return this.p2DiscardPile;
	}
	
	@Override
	public void playTrainerP2(long cardID) {
		// move the trainer card to the top of the discard pile
		this.p2DiscardPile.add(cardID);
		// removed the trainer card from player's hand
		this.p2Hand.remove(cardID);
	}
	
	
	// Methods for game
	@Override
	public int getTurn() {
		return this.turn;
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	@Override
	public long[] getPlayerIDs() {
		long[] playerIDs = {this.p1ID, this.p2ID};
		return playerIDs;
	}
	
	@Override
	public long[] getDeckIDs() {
		long[] deckIDs = {this.p1DeckID, this.p2DeckID};
		return deckIDs;
	}
	
	@Override
	public long getCurrentPlayerID() {
		return this.currentPlayerID;
	}
	
	@Override
	public void setCurrentPlayerID(long currentPlayerID) {
		this.currentPlayerID = currentPlayerID;
	}
	
	@Override
	public boolean isPlayerInGame(long playerID) {
		if(playerID == this.p1ID || playerID == this.p2ID) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isCardInPlayerHand(long playerID, long cardID) {
		if(playerID == this.p1ID) {
			return this.p1Hand.contains(cardID);
		} else {
			return this.p2Hand.contains(cardID);
		}
	}
	
	@Override
	public boolean isPokemonInBench(long playerID, long cardID) {
		
		if(playerID == this.p1ID) {
			for(int index=0; index < this.p1Bench.size(); index++) {
				long BPID = this.p1Bench.get(index).getBasePokemonID();
				long S1PID = this.p1Bench.get(index).getStageOnePokemonID();
				if(cardID == BPID || cardID == S1PID) {
					return true;
				}
			}
			return false;
			
		} else {
			for(int index=0; index < this.p2Bench.size(); index++) {
				long BPID = this.p2Bench.get(index).getBasePokemonID();
				long S1PID = this.p2Bench.get(index).getStageOnePokemonID();
				if(cardID == BPID || cardID == S1PID) {
					return true;
				}
			}
			return false;
		}
	}
	
	@Override
	public boolean checkPlayerPlayedEnergy(long playerID) {
		if(playerID == this.p1ID) {
			return (this.p1EPS == 1);
		} else {
			return (this.p2EPS == 1);
		}
	}
	
	public boolean checkPlayerPlayerEnergyCT(long playerID, long PID) {
		
		if(playerID == this.p1ID) {
			for(int index=0; index < this.p1Bench.size(); index++) {
				if(this.p1Bench.get(index).getBenchedPokemonID() == PID) {
					int lastUpdateTurn = this.p1Bench.get(index).getTurn();
					if(this.turn == lastUpdateTurn + 2) {
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		} else {
			for(int index=0; index < this.p2Bench.size(); index++) {
				if(this.p2Bench.get(index).getBenchedPokemonID() == PID) {
					int lastUpdateTurn = this.p2Bench.get(index).getTurn();
					if(this.turn == lastUpdateTurn + 2) {
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		}
	}
	
	@Override
	public void cleanEnergyPlayRecord() {
		if(this.currentPlayerID == this.p1ID) {
			this.p1EPS = 0;
		} else {
			this.p2EPS = 0;
		}
	}
	
	@Override
	public void checkPlayerHandSizeLimit(long playerID) {
		
		if(playerID == this.p1ID) {
			if(this.p1Hand.size() > 7) {
				long cardID = this.p1Hand.remove(0);
				this.p1DiscardPile.add(cardID);
			}
		} else {
			if(this.p2Hand.size() > 7) {
				long cardID = this.p2Hand.remove(0);
				this.p2DiscardPile.add(cardID);
			}
		}
	}
	
	@Override
	public void endPlayerTurn() {
		if(this.currentPlayerID == this.p1ID) {
			this.currentPlayerID = this.p2ID;
		} else {
			this.currentPlayerID = this.p1ID;
		}
	}
	
	@Override
	public long getPlayerDeckID(long playerID) {
		if(playerID == this.p1ID) {
			return this.p1DeckID;
		} else {
			return this.p2DeckID;
		}
	}
	
	@Override
	public int getPlayerCurrentDeckIndex() {
		if(this.currentPlayerID == this.p1ID) {
			return getP1DeckIndex();
		} else {
			return getP2DeckIndex();
		}
	}
	
	@Override
	public void addCardToPlayerHand(long cardID) {
		if(this.currentPlayerID == this.p1ID) {
			addCardToP1Hand(cardID);
		} else {
			addCardToP2Hand(cardID);
		}
	}
	
	public void evolvePokemon(long playerID, long S1PID, long BPID) {
		// S1PID: stage one pokemon id; BPID: base pokemon id
		
		if(playerID == this.p1ID) {
			// remove the stage one pokemon card from player's hand
			this.p1Hand.remove(S1PID);
			// replace benched basic pokemon
			for(int index=0; index < this.p1Bench.size(); index++) {
				if(this.p1Bench.get(index).getBenchedPokemonID() == BPID) {
					this.p1Bench.get(index).setStageOnePokemonID(S1PID);
					try {	
						UoW.getCurrent().registerDirty(this.p1Bench.get(index));
						UoW.getCurrent().commit();
					} catch (MissingMappingException | MapperException | InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}

		} else {
			// remove the stage one pokemon card from player's hand
			this.p2Hand.remove(S1PID);
			// replace benched basic pokemon
			for(int index=0; index < this.p2Bench.size(); index++) {
				if(this.p2Bench.get(index).getBenchedPokemonID() == BPID) {
					this.p2Bench.get(index).setStageOnePokemonID(S1PID);
					try {
						UoW.getCurrent().registerDirty(this.p2Bench.get(index));
						UoW.getCurrent().commit();
					} catch (MissingMappingException | MapperException | InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
	public void attachEnergy(long playerID, long EID, long PID) {
		
		if(playerID == this.p1ID) {
			// remove energy card from player's hand
			this.p1Hand.remove(EID);
			for(int index=0; index < this.p1Bench.size(); index++) {
				if(this.p1Bench.get(index).getBenchedPokemonID() == PID) {
					this.p1EPS = 1;
					this.p1Bench.get(index).setTurn(this.turn);
					this.p1Bench.get(index).attachEnergy(EID);
					try {
						
						UoW.getCurrent().registerDirty(this.p1Bench.get(index));
						UoW.getCurrent().commit();
					} catch (MissingMappingException | MapperException | InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		} else {
			// remove energy card from player's hand
			this.p2Hand.remove(EID);
			for(int index=0; index < this.p2Bench.size(); index++) {
				if(this.p2Bench.get(index).getBenchedPokemonID() == PID) {
					this.p2EPS = 1;
					this.p2Bench.get(index).setTurn(this.turn);
					this.p2Bench.get(index).attachEnergy(EID);
					try {
						UoW.getCurrent().registerDirty(this.p2Bench.get(index));
						UoW.getCurrent().commit();
					} catch (MissingMappingException | MapperException | InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
}
