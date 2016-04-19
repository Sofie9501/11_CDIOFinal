package interfaces;

import java.util.List;

import DTO.ProduktBatchAdminDTO;


public interface ProduktBatchAdminDAO {

	ProduktBatchAdminDTO getProduktBatchAdmin(int PbId) throws DALException;
	List getOperatoerList() throws DALException;
}
