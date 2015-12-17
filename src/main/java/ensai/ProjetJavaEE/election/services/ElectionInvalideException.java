package ensai.ProjetJavaEE.election.services;


import lombok.Builder;
import lombok.Getter;

public class ElectionInvalideException extends Exception{
	
	
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
		
		ELECTION_OBLIGATOIRE("L'election est obligatoire pour effectuer l'opération."),
		TITRE_OBLIGATOIRE("Le titre est obligatoire."),
		GERANT_OBLIGATOIRE("Une gérant est obligatoire."),
		DEBUT_OBLIGATOIRE("Une date de début est obligatoire."),
		FIN_OBLIGATOIRE("Une date de fin est obligatoire."),
		ELECTION_TERMINEE("L'election est cloturée"),
		ELECTION_EN_COURS("L'election est toujours en cours"),
		;

		@Getter
		public String message;

		private ErreurElection(String message) {
			this.message = message;
		}
		
	}

}
