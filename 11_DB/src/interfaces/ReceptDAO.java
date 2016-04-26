package interfaces;

import java.util.ArrayList;
import java.util.List;

import DTO.ReceptDTO;
import DTO.ReceptKompDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept, ArrayList<ReceptKompDTO> komp) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
