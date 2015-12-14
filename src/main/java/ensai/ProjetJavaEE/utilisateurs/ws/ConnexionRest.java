package ensai.ProjetJavaEE.utilisateurs.ws;

import java.io.File;
import java.net.URL;

import javax.servlet.RequestDispatcher;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.ConnexionService;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class ConnexionRest {
	
	File file = new File("TPJavaEE/src/webapp/index.html");

	@Autowired
	private ConnexionService connexionService;
	
	@Autowired
	private NotificationsServices notificationsServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public URL test(Utilisateur utilisateur)throws UtilisateurInvalideException {
		return null;
		
		 
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void connexion(Utilisateur utilisateur)throws UtilisateurInvalideException {
		
		log.info("=====> Connexion de l'utilisateur de login {}: {}.", utilisateur);
		
		try{
			connexionService.connexion(utilisateur.getLogin(),utilisateur.getMotDePasse());
		}catch(Exception e){
			notificationsServices.notifier("Erreur de connexion");
		}
	}
	

}
