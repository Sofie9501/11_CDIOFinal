package interfaces;

import java.util.List;
import DTO.ProduktBatchKomponentDTO;

public interface ProduktBatchKompDAO {
	ProduktBatchKomponentDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKompList(int pbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKompList() throws DALException;
	void createProduktBatchKomp(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException;
	void updateProduktBatchKomp(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException;	
}

