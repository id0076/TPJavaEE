package ensai.ProjetJavaEE.utilisateurs.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SuppressionUtilisateurService {
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurService;

	@Autowired
	private EntityManager entityManager;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void supprimer(String login) throws UtilisateurInvalideException {
		
		Utilisateur utilisateur = rechercherUtilisateurService.rechercherParLogin(login);
		
		if (utilisateur != null) {
			entityManager.remove(utilisateur);
		}else{
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_NONEXISTANT);
		}

	}

}
