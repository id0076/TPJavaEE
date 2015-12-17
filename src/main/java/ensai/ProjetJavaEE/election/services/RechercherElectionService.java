package ensai.ProjetJavaEE.election.services;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RechercherElectionService {
	
	@Autowired
	private EntityManager entityManager;
	
	public Election rechercherParTitre(String titre) {

		if (StringUtils.isNotBlank(titre)) {
			return entityManager.find(Election.class, titre);
		}

		return null;
		
	}

}
