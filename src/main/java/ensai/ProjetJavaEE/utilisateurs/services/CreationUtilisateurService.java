package ensai.ProjetJavaEE.utilisateurs.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class CreationUtilisateurService {

	@Autowired
	private ValidationUtilisateurServices validationServices;

	@Autowired
	private NotificationsServices notificationsServices;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurService;

	@Autowired
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public Utilisateur creer(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		log.info("=====> Création de l'utilisateur : {}.", utilisateur);

		if (utilisateur == null) {
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
		}

		if (rechercherUtilisateurService.rechercherParLogin(utilisateur.getLogin()) != null) {
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_EXISTANT);
		}
		
		validationServices.validerUtilisateur(utilisateur);

		notificationsServices.notifier("Création de l'utilisateur: " + utilisateur.toString());

		entityManager.persist(utilisateur);

		return utilisateur;
	}


}
