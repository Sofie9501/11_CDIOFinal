package interfaces;

import java.util.List;

import DTO.RaavareBatchDTO;

public interface RaavareBatchDAO {
	RaavareBatchDTO getRaavareBatch(int rbID) throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList(int raavareID) throws DALException;
	void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
	void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
}

