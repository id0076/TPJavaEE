package ensai.ProjetJavaEE.utilisateurs.services;



import java.io.IOException;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class ConnexionService {



	@Autowired
	private RechercherUtilisateurService rechercherService;


	public boolean connexion(String login, String mdp) throws UtilisateurInvalideException {

		Utilisateur util = rechercherService.rechercherParLogin(login);

		if (util != null) {

			if(util.getMotDePasse().equals(mdp)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

}




