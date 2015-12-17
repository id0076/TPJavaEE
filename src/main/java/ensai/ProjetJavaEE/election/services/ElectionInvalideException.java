package ensai.ProjetJavaEE.election.services;


import lombok.Builder;
import lombok.Getter;

public class ElectionInvalideException extends Exception{
	
	private static final long serialVersionUID = 6952445089012077657L;
	
	@Getter
	private ErreurElection erreur;

	@Builder
	public ElectionInvalideException(ErreurElection erreur, Throwable cause) {
		super(cause);

		this.erreur = erreur;
	}

	public ElectionInvalideException(ErreurElection erreur) {
		this.erreur = erreur;
	}
	
public enum ErreurElection {
		
		ELECTION_OBLIGATOIRE("L'élection est obligatoire pour effectuer l'opération."),
		TITRE_OBLIGATOIRE("Le titre est obligatoire."),
		GERANT_OBLIGATOIRE("Une gérant est obligatoire."),
		PROBLEME_DATE("Dates incompatibles"),
		ELECTION_TERMINEE("L'élection est cloturée"),
		ELECTION_EN_COURS("L'élection est toujours en cours"),
		ELECTION_EXISTANT("L'élection existe déjà"),
		ELECTION_INEXISTANT("L'élection n'existe pas"),
		DESCRIPTION_OBLIGATOIRE("Une description est obligatoire")
		;

		@Getter
		public String message;

		private ErreurElection(String message) {
			this.message = message;
		}
		
	}

}
