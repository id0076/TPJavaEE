package ensai.ProjetJavaEE.notifications.services;

import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationsServices {

	public String notifier(Action actU, Utilisateur u, String msg) {
		
		String message = "[" + actU.message + "]: " + u.getLogin() + " " + u.getEmail() + " " + msg + "\n";
		log.info("\t [" + actU.message + "]: \t'{}' \t'{}' \t'{}'", u.getLogin(), u.getEmail(), msg);
		
		return message;
		
	}

	public enum Action {

		CREATION_U("Création d'un utilisateur"),
		CREATION_V("Création d'une ville"),
		LISTAGE_U("Listage d'utilisateurs"),
		MODIFICATION_U("Modification d'un utilisateur"),
		RECHERCHE_U("Recherche d'un utilisateur"),
		SUPPRESSION_U("Suppression d'un utilisateur"),
		RECHERCHE_E("Recherche d'une élection"),
		CREATION_E("Création d'une élection"),
		CLOTURE_E("Cloturation d'une élection"),
		MODIFICATION_E("Modification d'une élection"),
		LISTAGE_E("Listage d'élections"),
		RESULTAT_E("Résultat d'une élection"),
		VOTE_E("Vote à une élection");

		@Getter
		public String message;

		private Action(String message) {
			this.message = message;
		}

	}
}
