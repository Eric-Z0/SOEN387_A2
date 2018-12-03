package org.dom.model.bench;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;

//ICard interface only provides methods to access card attributes
public interface IBench extends IDomainObject<Long> {
	
	public abstract long getBenchedPokemonID();
	
	public abstract long getBenchedBasePokemonID();
	
	public abstract long getBasePokemonID();
	
	public abstract void setBasePokemonID(long BPID);
	
	public abstract long getStageOnePokemonID();
	
	public abstract void setStageOnePokemonID(long S1PID);
	
	public abstract void attachEnergy(long EID);
	
	public abstract List<Long> getEnergyList();
	
	public abstract void setEnergyList(List<Long> energyList);
	
	public abstract int getTurn();
	
	public abstract void setTurn(int turn);

}
