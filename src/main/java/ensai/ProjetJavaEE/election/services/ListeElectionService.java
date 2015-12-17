package ensai.ProjetJavaEE.election.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.election.modele.Election;

@Service
public class ListeElectionService {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Election> listeElection() {
		
		List<?> liste = entityManager.createQuery("SELECT e FROM Election e").getResultList();
		List<Election> listeElection = new ArrayList<Election>();
		
		for(Object object : liste) {
			if(object instanceof Election) {
				listeElection.add((Election) object);
			}
		}
		
		return listeElection;
		
	}

	public List<Election> listeElectionTitre(String titre) {
		
		Query req = entityManager.createQuery("SELECT e FROM Election e WHERE e.titre LIKE :titre");
		req.setParameter("titre", "%" + titre + "%");
		
		List<?> liste = req.getResultList();
		List<Election> listeElection = new ArrayList<Election>();
		
		for(Object object : liste) {
			if(object instanceof Election) {
				listeElection.add((Election) object);
			}
		}
		
		return listeElection;
		
	}
	
	public List<Election> listeElectionCloture(boolean isCloture) {
		
		Query req = entityManager.createQuery("SELECT e FROM Election e");
		
		List<?> liste = req.getResultList();
		List<Election> listeElection = new ArrayList<Election>();
		
		for(Object object : liste) {
			if(object instanceof Election) {
				Election election = (Election) object;
				if(election.isCloture() == isCloture) {
					listeElection.add((Election) object);
				}
			}
		}
		
		return listeElection;
		
	}
	
	public List<Election> listeElectionCreateur(String createur) {
		
		Query req = entityManager.createQuery("SELECT e FROM Election e");
		
		List<?> liste = req.getResultList();
		List<Election> listeElection = new ArrayList<Election>();
		
		for(Object object : liste) {
			if(object instanceof Election) {
				Election election = (Election) object;
				if(election.getGerant().getLogin().equals(createur)) {
					listeElection.add((Election) object);
				}
			}
		}
		
		return listeElection;
		
	}

}
