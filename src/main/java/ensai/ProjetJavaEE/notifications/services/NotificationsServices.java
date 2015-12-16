package ensai.ProjetJavaEE.notifications.services;

import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationsServices {

	public void notifier(ActionUtilisateur actU, Utilisateur u, String msg) {
		
		log.info("\t [" + actU.message + "]: \t'{}' \t'{}' \t'{}'", u.getLogin(), u.getNom(), msg);
		
	}

	public enum ActionUtilisateur {

		CREATION("Création d'un utilisateur."),
		SUPPRESSION("Suppression d'un utilisateur."),
		MODIFICATION("Modification d'un utilisateur.");

		@Getter
		public String message;

		private ActionUtilisateur(String message) {
			this.message = message;
		}

	}
}
