package org.dom.model.bench.mappers;

import java.sql.SQLException;

import org.dom.model.bench.Bench;
import org.dom.model.bench.tdg.BenchTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;


public class BenchOutputMapper extends GenericOutputMapper<Long, Bench>{
	
	public void delete(Bench bench) throws MapperException {
		
		try {
			int count = BenchTDG.delete(bench.getId(), bench.getVersion());
			
			if(count == 0) throw new LostUpdateException("BenchTDG: id " + bench.getId() + " version " + bench.getVersion());
			
			bench.setVersion(bench.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete Bench " + bench.getId(), e);
		}
		
	}
	
	public void insert(Bench bench) throws MapperException {
		
		try {
			BenchTDG.insert(bench.getId(), bench.getVersion(), bench.getTurn(), bench.getBasePokemonID(), bench.getStageOnePokemonID(), bench.getEnergyList());
		} catch (SQLException e) {
			throw new MapperException("Could not insert Bench " + bench.getId(), e);
		}
		
	}
	
	public void update(Bench bench) throws MapperException {
		
		try {
			int count = BenchTDG.update(bench.getId(), bench.getVersion(), bench.getTurn(), bench.getBasePokemonID(), bench.getStageOnePokemonID(), bench.getEnergyList());
			
			if(count == 0) throw new LostUpdateException("BenchTDG: id " + bench.getId() + " version " + bench.getVersion());
			
			bench.setVersion(bench.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update Bench " + bench.getId(), e);
		}
		
	}
	

}