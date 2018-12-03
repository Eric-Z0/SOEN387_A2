package org.dom.model.bench;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

public class Bench extends DomainObject<Long> implements IBench {
	
	private long BPID;				// Base Pokemon Card ID
	private long S1PID;				// Stage One Pokemon Card ID
	private List<Long> energyList;	// Energies attached to the Pokemon
	private int turn;
	
	protected Bench(Long id, long version, int turn, long BPID, long S1PID, List<Long> energyList) {
		super(id, version);
		this.turn = turn;
		this.BPID = BPID;
		this.S1PID = S1PID;
		this.energyList = energyList;
	}
	
	// method for correctly displaying "id" part
	@Override
	public long getBenchedPokemonID() {
		if(this.S1PID != -1) {
			return this.S1PID;
		} else {
			return this.BPID;
		}
	}
	
	// method for correctly displaying "b" part
	@Override
	public long getBenchedBasePokemonID() {
		if(this.S1PID == -1) {
			return this.S1PID;
		} else {
			return this.BPID;
		}
	}
	
	@Override
	public int getTurn() {
		return this.turn;
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	@Override
	public long getBasePokemonID() {
		return this.BPID;
	}
	
	@Override
	public void setBasePokemonID(long BPID) {
		this.BPID = BPID;
	}
	
	@Override
	public long getStageOnePokemonID() {
		return this.S1PID;
	}
	
	@Override
	public void setStageOnePokemonID(long S1PID) {
		this.S1PID = S1PID;
	}
	
	@Override
	public void attachEnergy(long EID) {
		this.energyList.add(EID);
	}
	
	@Override
	public List<Long> getEnergyList() {
		List<Long> temp = new ArrayList<Long>();
		for(long energyID : this.energyList) {
			temp.add(energyID);
		}
		return temp;
	}
	
	@Override
	public void setEnergyList(List<Long> energyList) {
		this.energyList = new ArrayList<Long>();
		for(long energyID : energyList) {
			this.energyList.add(energyID);
		}
	}
	
}
