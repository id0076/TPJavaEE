package ensai.ProjetJavaEE.utilisateurs.services;

import lombok.Builder;
import lombok.Getter;

public class UtilisateurInvalideException extends Exception {

	private static final long serialVersionUID = -4966835559939948696L;

	@Getter
	private ErreurUtilisateur erreur;

	@Builder
	public UtilisateurInvalideException(ErreurUtilisateur erreur, Throwable cause) {
		super(cause);

		this.erreur = erreur;
	}

	public UtilisateurInvalideException(ErreurUtilisateur erreur) {
		this.erreur = erreur;
	}

	public enum ErreurUtilisateur {
		
		UTILISATEUR_OBLIGATOIRE("L'utilisateur est obligatoire pour effectuer l'opération."),
		NOM_OBLIGATOIRE("Le nom de l'utilisateur est obligatoire."),
		PRENOM_OBLIGATOIRE("Le prénom de l'utilisateur est obligatoire."),
		LOGIN_OBLIGATOIRE("Le login est obligatoire."),
		MDP_OBLIGATOIRE("Le mot de passe est obligatoire."),
		UTILISATEUR_EXISTANT("Un utilisateur de même login existe déjà sur le système.");

		@Getter
		public String message;

		private ErreurUtilisateur(String message) {
			this.message = message;
		}
		
	}
}
