package ensai.ProjetJavaEE.election.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ensai.ProjetJavaEE.utilisateurs.modele.Adresse;
import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur.UtilisateurBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Election {
	
	
	private Utilisateur gerant;
	@Id
	private String titre;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date debut;
	private Date fin;
	private int nbOui;
	private int nbNon;

}
