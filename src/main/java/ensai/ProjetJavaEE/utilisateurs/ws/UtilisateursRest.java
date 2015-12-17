package ensai.ProjetJavaEE.utilisateurs.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices.Action;
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
	
	@Autowired
	private NotificationsServices notificationsServices;
	
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
					notificationsServices.notifier(Action.CREATION_U, demandeur, utilisateur.toString());
				
				} catch (UtilisateurInvalideException e) {

					notificationsServices.notifier(Action.CREATION_U, demandeur, e.getErreur().message);
				
				}
				
			} else {

				notificationsServices.notifier(Action.CREATION_U, demandeur, "Droits d'administrateur nécessaires");
				
			}
		
		} else {

			notificationsServices.notifier(Action.CREATION_U, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Modification", method = RequestMethod.PUT)
	public void modifierUtilisateur(@PathVariable String login, @RequestBody Utilisateur utilisateur) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
				
				try {
					
					creationUtilisateurServices.modifier(utilisateur);
					notificationsServices.notifier(Action.CREATION_U, demandeur, utilisateur.toString());
				
				} catch (UtilisateurInvalideException e) {

					notificationsServices.notifier(Action.CREATION_U, demandeur, e.getErreur().message);
				
				}
				
			} else {

				notificationsServices.notifier(Action.CREATION_U, demandeur, "Droits d'administrateur nécessaires");
				
			}
		
		} else {

			notificationsServices.notifier(Action.CREATION_U, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Consultation", method = RequestMethod.GET)
	public Utilisateur consultationUtilisateur(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

			notificationsServices.notifier(Action.RECHERCHE_U, demandeur, login);
			return rechercherUtilisateurServices.rechercherParLogin(login);
		
		} else {

			notificationsServices.notifier(Action.RECHERCHE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Consultation/{loginConsulte}", method = RequestMethod.GET)
	public Utilisateur consultationUtilisateur(@PathVariable String login, @RequestParam String loginConsulte) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR) | login.equals(loginConsulte)) {

				notificationsServices.notifier(Action.RECHERCHE_U, demandeur, loginConsulte);
				return rechercherUtilisateurServices.rechercherParLogin(loginConsulte);
				
			} else {

				notificationsServices.notifier(Action.RECHERCHE_U, demandeur, "Identification nécessaires");
				return null;
				
			}
		
		} else {

			notificationsServices.notifier(Action.RECHERCHE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
		
	}

	@RequestMapping(value = "/Suppression", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			try{
				
				notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, login);
				suppressionUtilisateurServices.supprimer(login);
				
			}catch(UtilisateurInvalideException e){

				notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, e.getErreur().message);
			
			}
		
		} else {

			notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Suppression/{loginConsulte}", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable String login, @PathVariable String loginSupp) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
			
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR) | login.equals(loginSupp)) {

				try {

					notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, login);
					suppressionUtilisateurServices.supprimer(loginSupp);
					
				} catch (UtilisateurInvalideException e) {

					notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, e.getErreur().message);
					
				}
				
			} else {

				notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, "Droits d'administrateur nécessaires pour supprimer un autre utilisateur");
				
			}
			
		} else {

			notificationsServices.notifier(Action.SUPPRESSION_U, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Liste", method = RequestMethod.GET)
	public List<Utilisateur> liste(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Tout les utilisateurs");
				return listeUtilisateurServices.listeUtilisateur();
				
			} else {
	
				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Droits d'administrateur nécessaires");
				return null;
				
			}
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Nom/{nom}", method = RequestMethod.GET)
	public List<Utilisateur> listeNom(@PathVariable String login, @PathVariable String nom) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par nom: " + nom);
				return listeUtilisateurServices.listeUtilisateurNom(nom);
				
			} else {

				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Droits d'administrateur nécessaires");
				return null;
				
			} 
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Prenom/{prenom}", method = RequestMethod.GET)
	public List<Utilisateur> listePrenom(@PathVariable String login, @PathVariable String prenom) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {

				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par prénom: " + prenom);
				return listeUtilisateurServices.listeUtilisateurPrenom(prenom);
				
			} else {

				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Droits d'administrateur nécessaires");
				return null;
				
			} 
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Ville/{ville}", method = RequestMethod.GET)
	public List<Utilisateur> listeVille(@PathVariable String login, @PathVariable String ville) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par ville: " + ville);
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				return listeUtilisateurServices.listeUtilisateurVille(ville);
				
			} else {

				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Droits d'administrateur nécessaires");
				return null;
				
			} 
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Profil/{profil}", method = RequestMethod.GET)
	public List<Utilisateur> listeProfil(@PathVariable String login, @PathVariable String profil) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.ADMINISTRATEUR)) {
	
				if(profil.equals("ADMINISTRATEUR")) {
					
					notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par type: administrateur");
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.ADMINISTRATEUR);
					
				} else if(profil.equals("UTILISATEUR")) {

					notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par type: utilisateur");
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.UTILISATEUR);
					
				} else if(profil.equals("GERANT")) {

					notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Recherche par type: gérant");
					return listeUtilisateurServices.listeUtilisateurProfil(ProfilsUtilisateur.GERANT);
					
				} else {
					
					notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Type demandé inexistant");
					return null;
					
				}
				
			} else {

				notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Droits d'administrateur nécessaires");
				return null;
				
			} 
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_U, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}

}
