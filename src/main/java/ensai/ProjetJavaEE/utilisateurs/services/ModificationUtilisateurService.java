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
public class ModificationUtilisateurService {

	@Autowired
	private ValidationUtilisateurServices validationUtilisateurService;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurService;

	@Autowired
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void ModificationUtilisateur(Utilisateur utilisateur) throws UtilisateurInvalideException  {
		
		if (utilisateur == null) {
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
		}
		
		validationUtilisateurService.validerUtilisateur(utilisateur);
		
		if (rechercherUtilisateurService.rechercherParLogin(utilisateur.getLogin()) == null) {
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_NONEXISTANT);
		}
	
		entityManager.persist(utilisateur);
	
	}

}
