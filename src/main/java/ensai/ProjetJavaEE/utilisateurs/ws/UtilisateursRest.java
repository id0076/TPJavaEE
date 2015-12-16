package ensai.ProjetJavaEE.utilisateurs.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.CreationUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.ListeUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.RechercherUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.SuppressionUtilisateurService;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;

@RestController
@RequestMapping("{login}/Utilisateurs")
public class UtilisateursRest {
	
	//@Autowired
	//private NotificationsServices notificationsServices;
	
	@Autowired
	private CreationUtilisateurService creationUtilisateurServices;
	
	@Autowired
	private SuppressionUtilisateurService suppressionUtilisateurServices;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurServices;
	
	@Autowired
	private ListeUtilisateurService listeUtilisateurServices;

	@RequestMapping(value = "/Creation", method = RequestMethod.PUT)
	public void creerUtilisateur(@PathVariable String login, @RequestBody Utilisateur utilisateur) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
				
				try {
					
					creationUtilisateurServices.creer(utilisateur);
					//notificationsServices.notifier("Création de l'utilisateur " + utilisateur.getLogin() + " OK.");
				
				} catch (UtilisateurInvalideException e) {
				
					//notificationsServices.notifier("Erreur de création: " + e.getMessage());
				
				}
				
			} else {
				
				//notificationsServices.notifier("Erreur de création: Droits administrateurs nécessaires.");
				
			}
		
		} else {
			
			
			
		}
		
	}
	
	@RequestMapping(value = "/Consultation", method = RequestMethod.GET)
	public Utilisateur consultationUtilisateur(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			//notificationsServices.notifier("Consultation du profil de " + login);
			return rechercherUtilisateurServices.rechercherParLogin(login);
		
		} else {
			
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Consultation/{loginConsulte}", method = RequestMethod.GET)
	public Utilisateur consultationUtilisateur(@PathVariable String login, @PathVariable String loginConsulte) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR) | login.equals(loginConsulte)) {
	
				//notificationsServices.notifier("Consultation du profil de " + loginConsulte);
				return rechercherUtilisateurServices.rechercherParLogin(loginConsulte);
				
			} else {
				
				//notificationsServices.notifier("Erreur de consultation: Droits administrateurs nécessaires.");
				return null;
				
			}
		
		} else {
			
			return null;
			
		}
		
		
	}

	@RequestMapping(value = "/Suppression", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			try{
				
				suppressionUtilisateurServices.supprimer(login);
				
			}catch(Exception e){
				
				//notificationsServices.notifier("Erreur de suppression");
			
			}
		
		} else {
			
			
			
		}
		
	}
	
	@RequestMapping(value = "/Suppression/{loginConsulte}", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable String login, @PathVariable String loginSupp) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
			
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR) | login.equals(loginSupp)) {

				try {
					
					suppressionUtilisateurServices.supprimer(loginSupp);
					
				} catch (UtilisateurInvalideException e) {
					
					
					
				}
				
			} else {
				
				//notificationsServices.notifier("Erreur de suppression: Droits administrateurs nécessaires.");
				
			}
			
		} else {
			
			
			
		}
		
	}
	
	@RequestMapping(value = "/Liste", method = RequestMethod.GET)
	public List<Utilisateur> liste(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {

			return listeUtilisateurServices.listeUtilisateur();
			
		} else {
			
			//notificationsServices.notifier("Erreur de listage: Droits administrateurs nécessaires.");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Nom/{nom}", method = RequestMethod.GET)
	public List<Utilisateur> listeNom(@PathVariable String login, @PathVariable String nom) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				return listeUtilisateurServices.listeUtilisateurNom(nom);
				
			} else {

				//notificationsServices.notifier("Erreur de listage: Droits administrateurs nécessaires.");
				return null;
				
			} 
		
		} else {
			
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Prenom/{prenom}", method = RequestMethod.GET)
	public List<Utilisateur> listePrenom(@PathVariable String login, @PathVariable String prenom) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				return listeUtilisateurServices.listeUtilisateurPrenom(prenom);
				
			} else {

				//notificationsServices.notifier("Erreur de listage: Droits administrateurs nécessaires.");
				return null;
				
			} 
		
		} else {
			
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Ville/{ville}", method = RequestMethod.GET)
	public List<Utilisateur> listeVille(@PathVariable String login, @PathVariable String ville) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				return listeUtilisateurServices.listeUtilisateurVille(ville);
				
			} else {

				//notificationsServices.notifier("Erreur de listage: Droits administrateurs nécessaires.");
				return null;
				
			} 
		
		} else {
			
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Profil/{profil}", method = RequestMethod.GET)
	public List<Utilisateur> listeProfil(@PathVariable String login, @PathVariable String profil) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				if(profil.equals("ADMINISTRATEUR")) {
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.ADMINISTRATEUR);
				} else if(profil.equals("UTILISATEUR")) {
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.UTILISATEUR);
				} else if(profil.equals("GERANT")) {
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.GERANT);
				} else {
					return null;
				}
				
			} else {

				//notificationsServices.notifier("Erreur de listage: Droits administrateurs nécessaires.");
				return null;
				
			} 
		
		} else {
			
			return null;
			
		}
		
	}

}
