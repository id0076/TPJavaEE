package ensai.ProjetJavaEE.election.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.utilisateurs.services.CreationUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class OuvertureService {

	@Autowired
	EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public Election creer(Election election) throws UtilisateurInvalideException{
		log.info("=====> Création de l'élection : {}.", election);

		if (election == null) {
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
		}

		em.persist(election);

		return election;
	}

}



