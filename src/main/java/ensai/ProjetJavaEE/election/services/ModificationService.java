package ensai.ProjetJavaEE.election.services;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class ModificationService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private RechercherElectionService rechercherService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifierTitre(String ancien, String nouveau) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(ancien);
		if(election!=null){
			election.setTitre(nouveau);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifierGerant(String titre, Utilisateur utilisateur) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			election.setGerant(utilisateur);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifierDebut(String titre, Date debut) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			election.setDebut(debut);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifierFin(String titre, Date fin) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			election.setFin(fin);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifierDescription(String titre, String des) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			election.setDescription(des);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void incrementerOui(String titre) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			int nbOui =election.getNbOui()+1;
			election.setNbOui(nbOui);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void incrementerNon(String titre) throws ElectionInvalideException{
		Election election = rechercherService.rechercherParTitre(titre);
		if(election!=null){
			int nbNon =election.getNbNon()+1;
			election.setNbNon(nbNon);
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		em.persist(election);
	}
}
