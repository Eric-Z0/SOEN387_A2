package org.dom.model.bench;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.bench.tdg.BenchTDG;


public class BenchFactory {
	
	public static Bench createNew(int turn, long BPID, long S1PID, List<Long> energyList) throws SQLException, MapperException {
		Bench result = new Bench(BenchTDG.getMaxId(), 1, turn, BPID, S1PID, energyList);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static Bench createClean(long id, long version, int turn, long BPID, long S1PID, List<Long> energyList) throws SQLException {
		Bench result = new Bench(id, version, turn, BPID, S1PID, energyList);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
