package ensai.ProjetJavaEE.election.modele;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Election {
	
	@ManyToOne
	private Utilisateur gerant;
	@Id
	private String titre;
	private String description;
	private int nbOui = 0;
	private int nbNon = 0;
	private int nbBlanc = 0;
	private boolean cloture = false;
	@Temporal(TemporalType.DATE)
	private Date dateCloture;
	@ElementCollection(targetClass = String.class)
	@CollectionTable(name = "votantsUtilisateurs")
	@Singular
	private List<String> votants;
	
	public String toHTML() {
		
		String stringReturn = 
				  "  Titre:         " + titre + "<br/>"
				+ "  Descritpion:   " + description + "<br/>"
				+ "  Gérant:        " + gerant.getLogin() + "<br/>"
				+ "  Oui: " + nbOui + " Non: " + nbNon + " Blanc: " + nbBlanc;
		
		return stringReturn;
		
	}

}
