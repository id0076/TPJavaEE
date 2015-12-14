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
public class SuppressionUtilisateurService {
	
	@Autowired
	private NotificationsServices notificationsServices;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurService;

	@Autowired
	private EntityManager entityManager;

	
	
	public void supprimer(String login) throws UtilisateurInvalideException {
		
		log.info("=====> Suppression de l'utilisateur : {}.", login);
		
		if (rechercherUtilisateurService.rechercherParLogin(login) != null) {
		entityManager.remove(entityManager.find(Utilisateur.class, login));
		}else{
			throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
		}
		
		notificationsServices.notifier("Suppression de l'utilisateur: " + entityManager.find(Utilisateur.class, login).toString());

	}

}
