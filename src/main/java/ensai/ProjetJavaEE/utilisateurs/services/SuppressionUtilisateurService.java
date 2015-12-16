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
	
	public void supprimer(String login) throws UtilisateurInvalideException {
		
		if (rechercherUtilisateurService.rechercherParLogin(login) != null) {
			
			entityManager.remove(entityManager.find(Utilisateur.class, login));
		
		} else {
			
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
			
		}

	}

}
