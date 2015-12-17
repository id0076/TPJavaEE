package ensai.ProjetJavaEE.election.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;

@Service
public class ValidationElectionServices {

	public boolean validerElection(Election election) throws ElectionInvalideException {
		
		if (election == null) {
			
			return false;
			
		}

		validerTitre(election);
		validerDescritpion(election);
		validerGerant(election);

		return true;
		
	}

	private void validerTitre(Election election) throws ElectionInvalideException {
		
		if (StringUtils.isBlank(election.getTitre())) {
			
			throw new ElectionInvalideException(ErreurElection.TITRE_OBLIGATOIRE);
						
		}
	}
	
	private void validerDescritpion(Election election) throws ElectionInvalideException {
		
		if (StringUtils.isBlank(election.getDescription())) {
			
			throw new ElectionInvalideException(ErreurElection.DESCRIPTION_OBLIGATOIRE);
					
		}
	}
	
	private void validerGerant(Election election) throws ElectionInvalideException {
		
		if (election.getGerant() == null) {
			
			throw new ElectionInvalideException(ErreurElection.GERANT_OBLIGATOIRE);		
			
		}
	}

}
