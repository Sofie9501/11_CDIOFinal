package interfaces;

import java.util.List;

import DTO.ProduktBatchAdminDTO;


public interface ProduktBatchAdminDAO {

	ProduktBatchAdminDTO getProduktBatchAdmin(int PbId) throws DALException;
	List<ProduktBatchAdminDTO> getProduktBatchAdminList() throws DALException;
	void createProduktBatch(ProduktBatchAdminDTO produktbatchkomponentadmin) throws DALException;
}
