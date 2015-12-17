package ensai.ProjetJavaEE.election.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;

@Service
public class VoteElectionService {

	@Autowired
	private RechercherElectionService rechercherElectionService;
	
	@Autowired
	private CreationElectionService modificationElectionService;
	
	public void voteOui(String titre, String login) throws ElectionInvalideException {
		
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
			
		}
		
		if(!election.getVotants().contains(login) && !election.isCloture()) {
			election.getVotants().add(login);
			election.setNbOui(election.getNbOui() + 1);
		}
		
		modificationElectionService.modifier(election);
		
	}
	
	public void voteNon(String titre, String login) throws ElectionInvalideException {
		
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
			
		}
		
		if(!election.getVotants().contains(login) && !election.isCloture()) {
			election.getVotants().add(login);
			election.setNbOui(election.getNbNon() + 1);
		}
		
		modificationElectionService.modifier(election);
		
	}
	
	public void voteBlanc(String titre, String login) throws ElectionInvalideException {
		
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(election == null) {
			
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
			
		}
		
		if(!election.getVotants().contains(login) && !election.isCloture()) {
			election.getVotants().add(login);
			election.setNbOui(election.getNbBlanc() + 1);
		}
		
		modificationElectionService.modifier(election);
		
	}

}
