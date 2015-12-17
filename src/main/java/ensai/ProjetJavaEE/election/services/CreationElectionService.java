package ensai.ProjetJavaEE.election.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CreationElectionService {

	@Autowired
	private ValidationElectionServices validationElectionServices;
	
	@Autowired
	private RechercherElectionService rechercherElectionService;

	@Autowired
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public Election creer(Election election) throws ElectionInvalideException {

		if (election == null) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}

		if (rechercherElectionService.rechercherParTitre(election.getTitre()) != null) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_EXISTANT);
		}
		
		validationElectionServices.validerElection(election);

		entityManager.persist(election);

		return election;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Election modifier(Election election) throws ElectionInvalideException {

		if (election == null) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}

		if (rechercherElectionService.rechercherParTitre(election.getTitre()) == null) {
			throw new ElectionInvalideException(ErreurElection.ELECTION_INEXISTANT);
		}
		
		validationElectionServices.validerElection(election);

		entityManager.persist(election);

		return election;
		
	}


}
