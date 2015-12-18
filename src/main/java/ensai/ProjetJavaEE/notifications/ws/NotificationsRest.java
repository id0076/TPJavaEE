package ensai.ProjetJavaEE.notifications.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices.Action;
import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.RechercherUtilisateurService;

@RestController
@RequestMapping("{login}/")
public class NotificationsRest {

	@Autowired
	private NotificationsServices notificationsServices;

	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurServices;

	@RequestMapping(value = "/Log", method = RequestMethod.GET)
	public String listeLog(@PathVariable String login) {
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);

		if (demandeur != null) {

			if (demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {

				notificationsServices.notifier(Action.LOG, demandeur, "Affichage de l'ensemble des logs");
				return notificationsServices.log();

			} else {

				notificationsServices.notifier(Action.LOG, demandeur, "Droits d'administrateur nécessaires");
				return null;

			}

		} else {

			notificationsServices.notifier(Action.LOG, demandeur, "Identification nécessaires");
			return null;

		}

	}

	@RequestMapping(value = "/LogLogin/{logLogin}", method = RequestMethod.GET)
	public String listeLogLogin(@PathVariable String login, @PathVariable String logLogin) {
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);

		if (demandeur != null) {

			if (demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {

				notificationsServices.notifier(Action.LOG, demandeur,
						"Affichage des logs correspondant à l'utilisateur dont le login contient <" + logLogin + ">");
				return notificationsServices.logLogin(logLogin);

			} else {

				notificationsServices.notifier(Action.LOG, demandeur, "Droits d'administrateur nécessaires");
				return null;

			}

		} else {

			notificationsServices.notifier(Action.LOG, demandeur, "Identification nécessaires");
			return null;

		}

	}

	@RequestMapping(value = "/LogAction/{logAction}", method = RequestMethod.GET)
	public String listeLogActipon(@PathVariable String login, @PathVariable String logAction) {
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);

		if (demandeur != null) {

			if (demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {

				notificationsServices.notifier(Action.LOG, demandeur,
						"Affichage des logs correspondant à l'action dont le nom contient <" + logAction + ">");
				return notificationsServices.logAction(logAction);

			} else {

				notificationsServices.notifier(Action.LOG, demandeur, "Droits d'administrateur nécessaires");
				return null;

			}

		} else {

			notificationsServices.notifier(Action.LOG, demandeur, "Identification nécessaires");
			return null;

		}

	}
}
