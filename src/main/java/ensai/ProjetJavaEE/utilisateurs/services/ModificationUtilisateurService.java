package ensai.ProjetJavaEE.utilisateurs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;

@Service
public class ModificationUtilisateurService {
	
	@Autowired
	private RechercherUtilisateurService rechercheUtilisateurService;
	
	@Autowired
	private CreationUtilisateurService creationUtilisateurService;

	@Transactional(propagation = Propagation.REQUIRED)
	public void ModificationEmailUtilisateur(String nouveauEmail, String login) {
		Utilisateur utilisateur = rechercheUtilisateurService.rechercherParLogin(login);
		utilisateur.setEmail(nouveauEmail);
		try {
			creationUtilisateurService.creer(utilisateur);
		} catch (UtilisateurInvalideException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
