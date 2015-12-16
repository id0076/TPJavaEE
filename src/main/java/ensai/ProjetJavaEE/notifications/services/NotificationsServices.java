package ensai.ProjetJavaEE.notifications.services;

import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationsServices {

	public void notifier(ActionUtilisateur actU, Utilisateur u, String msg) {
		
		log.info("\t [" + actU.message + "]: \t'{}' \t'{}' \t'{}'", u.getLogin(), u.getEmail(), msg);
		
	}

	public enum ActionUtilisateur {

		CREATION_U("Création d'un utilisateur."),
		CREATION_V("Création d'une ville."),
		LISTAGE("Listage d'utilisateur."),
		MODIFICATION("Modification d'un utilisateur."),
		RECHERCHE("Recherche d'un utilisateur."),
		SUPPRESSION("Suppression d'un utilisateur.");

		@Getter
		public String message;

		private ActionUtilisateur(String message) {
			this.message = message;
		}

	}
}
