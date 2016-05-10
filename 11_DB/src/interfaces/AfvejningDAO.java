package interfaces;

import DTO.AfvejningDTO;
import DTO.ProduktBatchKomponentDTO;

public interface AfvejningDAO {
	public AfvejningDTO getAfvejning(int pb_id) throws DALException;
	public void createProduktbatchKomponent(ProduktBatchKomponentDTO pbDTO) throws DALException;
}
