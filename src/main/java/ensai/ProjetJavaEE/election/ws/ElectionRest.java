package ensai.ProjetJavaEE.election.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ClotureElectionService;
import ensai.ProjetJavaEE.election.services.CreationElectionService;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException;
import ensai.ProjetJavaEE.election.services.ListeElectionService;
import ensai.ProjetJavaEE.election.services.RechercherElectionService;
import ensai.ProjetJavaEE.election.services.VoteElectionService;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices.Action;
import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.RechercherUtilisateurService;

@RestController
@RequestMapping("{login}/Elections")
public class ElectionRest {

	@Autowired
	private RechercherElectionService rechercherElectionService;
	
	@Autowired
	private RechercherUtilisateurService rechercherUtilisateurServices;

	@Autowired
	private NotificationsServices notificationsServices;
	
	@Autowired
	private CreationElectionService creationElectionService;

	@Autowired
	private ClotureElectionService clotureElectionService;
	
	@Autowired
	private ListeElectionService listeElectionService;
	
	@Autowired
	private VoteElectionService voteElectionService;

	@RequestMapping(value = "/Consultation/{titre}", method = RequestMethod.GET)
	public Election consultationElection(@PathVariable String login, @PathVariable String titre) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

			notificationsServices.notifier(Action.RECHERCHE_E, demandeur, login);
			return rechercherElectionService.rechercherParTitre(titre);
			
		} else {

			notificationsServices.notifier(Action.RECHERCHE_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Creation", method = RequestMethod.PUT)
	public void creerElection(@PathVariable String login, @RequestBody Election election) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.GERANT)) {
				
				try {
					
					creationElectionService.creer(election);
					notificationsServices.notifier(Action.CREATION_E, demandeur, election.toString());
				
				} catch (ElectionInvalideException e) {

					notificationsServices.notifier(Action.CREATION_E, demandeur, e.getErreur().message);
				
				}
				
			} else {

				notificationsServices.notifier(Action.CREATION_E, demandeur, "Droits de gérant nécessaires");
				
			}
		
		} else {

			notificationsServices.notifier(Action.CREATION_E, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Cloture/{titre}", method = RequestMethod.PUT)
	public void clotureElection(@PathVariable String login, @PathVariable String titre) {

		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		Election election = rechercherElectionService.rechercherParTitre(titre);
		
		if(demandeur != null) {
			
			if(election != null) {
		
				if(demandeur.getLogin().equals(election.getGerant().getLogin())) {
					
					try {
						
						clotureElectionService.cloture(titre);
						notificationsServices.notifier(Action.CLOTURE_E, demandeur, election.toString());
					
					} catch (ElectionInvalideException e) {
	
						notificationsServices.notifier(Action.CLOTURE_E, demandeur, e.getErreur().message);
					
					}
					
				} else {
	
					notificationsServices.notifier(Action.CLOTURE_E, demandeur, "Seul le gérant peut cloturer son élection");
					
				}
			
			} else {
				
				notificationsServices.notifier(Action.CLOTURE_E, demandeur, "Cette élection n'existe pas");
				
			}
		
		} else {

			notificationsServices.notifier(Action.CLOTURE_E, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Modification", method = RequestMethod.PUT)
	public void modifierElection(@PathVariable String login, @RequestBody Election election) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {
		
			if(demandeur.getProfils().contains(ProfilsUtilisateur.GERANT) & demandeur.getLogin().equals(election.getGerant().getLogin())) {
				
				try {
					
					creationElectionService.modifier(election);
					notificationsServices.notifier(Action.MODIFICATION_E, demandeur, election.toString());
				
				} catch (ElectionInvalideException e) {

					notificationsServices.notifier(Action.MODIFICATION_E, demandeur, e.getErreur().message);
				
				}
				
			} else {

				notificationsServices.notifier(Action.MODIFICATION_E, demandeur, "Une élection ne peut être modifier que par son gérant");
				
			}
		
		} else {

			notificationsServices.notifier(Action.MODIFICATION_E, demandeur, "Identification nécessaires");
			
		}
		
	}
	
	@RequestMapping(value = "/Liste", method = RequestMethod.GET)
	public List<Election> liste(@PathVariable String login) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Listage des élections");
				return listeElectionService.listeElection();
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Titre/{titre}", method = RequestMethod.GET)
	public List<Election> listeTitre(@PathVariable String login, @PathVariable String titre) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Recherche par titre: " + titre);
				return listeElectionService.listeElectionTitre(titre);
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Createur/{createur}", method = RequestMethod.GET)
	public List<Election> listeCreateur(@PathVariable String login, @PathVariable String createur) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Recherche par créateur: " + createur);
				return listeElectionService.listeElectionCreateur(createur);
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Liste/Cloture/{cloture}", method = RequestMethod.GET)
	public List<Election> listeCloture(@PathVariable String login, @PathVariable String cloture) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Recherche par cloture: " + cloture);
				if(cloture == "True") {
					return listeElectionService.listeElectionCloture(true);
				} else if(cloture == "False") {
					return listeElectionService.listeElectionCloture(false);
				} else {
					return null;
				}
		
		} else {

			notificationsServices.notifier(Action.LISTAGE_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Resultat/{titre}", method = RequestMethod.GET)
	public String resultat(@PathVariable String login, @PathVariable String titre) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				Election election = rechercherElectionService.rechercherParTitre(titre);
				
				if(election != null) {
					
					return election.toHTML();
					
				} else {
					
					notificationsServices.notifier(Action.RESULTAT_E, demandeur, "Cette élection n'existe pas");
					return null;
					
				}
		
		} else {

			notificationsServices.notifier(Action.RESULTAT_E, demandeur, "Identification nécessaires");
			return null;
			
		}
		
	}
	
	@RequestMapping(value = "/Vote/{titre}/{vote}", method = RequestMethod.PUT)
	public void vote(@PathVariable String login, @PathVariable String titre, @PathVariable String vote) {
		
		Utilisateur demandeur = rechercherUtilisateurServices.rechercherParLogin(login);
		
		if(demandeur != null) {

				Election election = rechercherElectionService.rechercherParTitre(titre);
				
				if(election != null) {
					
					try {
						if(vote.equals("Oui")) {
							voteElectionService.voteOui(titre, login);
						} else if(vote.equals("Non")) {
							voteElectionService.voteNon(titre, login);
						}  else if(vote.equals("Blanc")) {
							voteElectionService.voteBlanc(titre, login);
						}
					} catch(ElectionInvalideException e) {
						notificationsServices.notifier(Action.VOTE_E, demandeur, e.getErreur().message);
					}
					
				} else {
					
					notificationsServices.notifier(Action.VOTE_E, demandeur, "Cette élection n'existe pas");
					
				}
		
		} else {

			notificationsServices.notifier(Action.VOTE_E, demandeur, "Identification nécessaires");
			
		}
		
	}
	
}
