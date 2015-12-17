package ensai.ProjetJavaEE.election.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ClotureElectionService {

	@Autowired
	private RechercherElectionService rechercherElectionService;
	
	@Autowired
	private CreationElectionService creationElectionService;
	
	public boolean isOver(String titre) throws ElectionInvalideException {
		
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
			
		}
		
		return true;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void cloture(String titre) throws ElectionInvalideException {
		
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
			
		}

		election.setCloture(true);
		election.setDateCloture(new Date());

		creationElectionService.modifier(election);
		
	}

}
