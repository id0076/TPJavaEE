package ensai.ProjetJavaEE;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.CreationElectionService;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException;
import ensai.ProjetJavaEE.utilisateurs.modele.Adresse;
import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Ville;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;
import ensai.ProjetJavaEE.utilisateurs.services.CreationUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.CreationVilleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Slf4j
public class Initialisation {

	@Autowired
	private CreationVilleService villeService;
	
	@Autowired
	private CreationUtilisateurService creationUtilisateurService;
	
	@Autowired
	private CreationElectionService creationElectionService;

	@SuppressWarnings("deprecation")
	@PostConstruct
	@Transactional(propagation = Propagation.REQUIRED)
	public void init() {
		
		log.info("Initialisation des villes par défaut dans la base...");
		
		Ville rennes = new Ville();
		rennes.setCodePostal("35000");
		rennes.setNom("Rennes");

		villeService.creer(rennes);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin("David");
		utilisateur.setNom("Lesurque");
		utilisateur.setPrenom("David");
		utilisateur.setMotDePasse("password");
		utilisateur.setDateDeNaissance(new Date(1993, 11, 19));
		utilisateur.setEmail("davidlesurque@gmail.com");
		
		utilisateur.setProfils(new ArrayList<ProfilsUtilisateur>());
		utilisateur.getProfils().add(ProfilsUtilisateur.ADMINISTRATEUR);
		
		Adresse adresse = new Adresse();
		adresse.setVille(rennes);
		adresse.setRue("République");	
		
		utilisateur.setAdresse(adresse);
		
		try {
			creationUtilisateurService.creer(utilisateur);
			System.out.println("Utilisateur créé");
		} catch (UtilisateurInvalideException e) {
			e.printStackTrace();
		}
		
		Election election = new Election();
		election.setTitre("ElectionTest");
		election.setDescription("Test");
		election.setGerant(utilisateur);
		
		try {
			creationElectionService.creer(election);
		} catch (ElectionInvalideException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}