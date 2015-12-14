package ensai.ProjetJavaEE.utilisateurs.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.CreationUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.RechercherUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.SuppressionUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("utilisateurs/{login}")
@Slf4j
public class UtilisateursRest {
	
	@Autowired
	private NotificationsServices notificationsServices;
	
	@Autowired
	private CreationUtilisateurService creationUtilisateurServices;
	
	@Autowired
	private SuppressionUtilisateurService suppressionUtilisateurServices;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurServices;

	@RequestMapping(method = RequestMethod.PUT)
	public void creer(@PathVariable String login, @RequestBody Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		log.info("=====> Création ou modification de l'utilisateur de login {}: {}.", login, utilisateur);
		
		utilisateur.setLogin(login);
		
		try{
			creationUtilisateurServices.creer(utilisateur);
		}catch(Exception e){
			notificationsServices.notifier("Erreur de création");
		}
		
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void supprimer(@PathVariable String login) throws UtilisateurInvalideException {
		
		log.info("=====> Suppression de l'utilisateur de login {}.", login);
		
		try{
			suppressionUtilisateurServices.supprimer(login);
		}catch(Exception e){
			notificationsServices.notifier("Erreur de suppression");
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Utilisateur lire(@PathVariable String login) {
		
		log.info("=====> Récupération de l'utilisateur de login {}.", login);

		return rechercherUtilisateurServices.rechercherParLogin(login);
		
	}

	@ExceptionHandler({ UtilisateurInvalideException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public DescriptionErreur gestionErreur(UtilisateurInvalideException exception) {
		
		return new DescriptionErreur(exception.getErreur().name(), exception.getErreur().getMessage());
		
	}
}
