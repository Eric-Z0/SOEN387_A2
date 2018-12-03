package org.dom.model.bench;

import java.sql.SQLException;
import java.util.List;

import org.dom.model.bench.mappers.BenchInputMapper;
import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;


public class BenchProxy extends DomainObjectProxy<Long, Bench> implements IBench {
	
	public BenchProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Bench getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return BenchInputMapper.find(id);
		} catch (MapperException | SQLException e) {
			throw new DomainObjectCreationException(e.getMessage(), e);
		}
	}
	
	@Override
	public long getBenchedPokemonID() {
		return getInnerObject().getBenchedPokemonID();
	}
	
	@Override
	public long getBenchedBasePokemonID() {
		return getInnerObject().getBenchedBasePokemonID();
	}
	
	@Override
	public long getBasePokemonID() {
		return getInnerObject().getBasePokemonID();
	}
	
	@Override
	public void setBasePokemonID(long BPID) {
		getInnerObject().setBasePokemonID(BPID);
	}
	
	@Override
	public long getStageOnePokemonID() {
		return getInnerObject().getStageOnePokemonID();
	}
	
	@Override
	public void setStageOnePokemonID(long S1PID) {
		getInnerObject().setStageOnePokemonID(S1PID);
	}
	
	@Override
	public void attachEnergy(long EID) {
		getInnerObject().attachEnergy(EID);
	}
	
	@Override
	public List<Long> getEnergyList() {
		return getInnerObject().getEnergyList();
	}
	
	@Override
	public void setEnergyList(List<Long> energyList) {
		getInnerObject().setEnergyList(energyList);
	}
	
	@Override
	public int getTurn() {
		return getInnerObject().getTurn();
	}
	
	@Override
	public void setTurn(int turn) {
		getInnerObject().setTurn(turn);
	}

}
