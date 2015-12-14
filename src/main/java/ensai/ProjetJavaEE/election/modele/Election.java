package ensai.ProjetJavaEE.election.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

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
	
	Utilisateur gerant;
	String titre;
	String description;
	Date debut;
	Date fin;
	int nbOui;
	int nbNon;

}
