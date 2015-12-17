package ensai.ProjetJavaEE.election.services;

import java.security.Timestamp;
import java.util.Date;

import javax.tools.JavaFileObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class ConsultationService {

	@Autowired
	private RechercherElectionService rechercherElection;

	public Election consulterResultats(String titre) throws ElectionInvalideException{
		Date actuelle = new Date();
		Election election = rechercherElection.rechercherParTitre(titre);
		
		if(election!=null){
		
			if(election.getFin().before(actuelle)){
				return election;
			
			}else{
				throw new ElectionInvalideException(ErreurElection.ELECTION_EN_COURS);
			}
		
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}

	}
}
