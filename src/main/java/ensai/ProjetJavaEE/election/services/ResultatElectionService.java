package ensai.ProjetJavaEE.election.services;

import org.springframework.beans.factory.annotation.Autowired;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;

public class ResultatElectionService {

	@Autowired
	private RechercherElectionService rechercheElectionService;
	
	public String resultat(String titre) throws ElectionInvalideException {

		Election election = rechercheElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
		}
		
		if(!election.isCloture()) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_EN_COURS);
		}

		return "Election: " + election.getTitre() + " Oui: " + election.getNbOui() + " Non: " + election.getNbNon();
	}

}
