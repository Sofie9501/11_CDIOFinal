package interfaces;

import DTO.AfvejningDTO;

public interface AfvejningDAO {
	public AfvejningDTO getAfvejning(int pb_id) throws DALException;
	
	
}
