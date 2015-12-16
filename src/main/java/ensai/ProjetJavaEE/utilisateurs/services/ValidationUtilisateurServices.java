package ensai.ProjetJavaEE.utilisateurs.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;

@Service
public class ValidationUtilisateurServices {

	public boolean validerUtilisateur(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		if (utilisateur == null) {
			
			return false;
			
		}

		validerLogin(utilisateur);
		validerNom(utilisateur);
		validerPrenom(utilisateur);
		validerMotDePasse(utilisateur);

		return true;
		
	}

	private void validerNom(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		if (StringUtils.isBlank(utilisateur.getNom())) {
			
			throw new UtilisateurInvalideException(ErreurUtilisateur.NOM_OBLIGATOIRE);
			
			
		}
	}

	private void validerPrenom(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		if (StringUtils.isBlank(utilisateur.getPrenom())) {
			
			throw new UtilisateurInvalideException(ErreurUtilisateur.PRENOM_OBLIGATOIRE);
			
		}
	
	}

	private void validerLogin(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		if (StringUtils.isBlank(utilisateur.getLogin())) {
			
			throw new UtilisateurInvalideException(ErreurUtilisateur.LOGIN_OBLIGATOIRE);
			
		}
		
	}

	private void validerMotDePasse(Utilisateur utilisateur) throws UtilisateurInvalideException {
		
		if (StringUtils.isBlank(utilisateur.getMotDePasse())) {
			
			throw new UtilisateurInvalideException(ErreurUtilisateur.MDP_OBLIGATOIRE);
			
		}
		
	}
}
