package ensai.ProjetJavaEE.utilisateurs.services;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RechercherUtilisateurService {

	@Autowired
	private EntityManager entityManager;
	
	public Utilisateur rechercherParLogin(String login) {

		if (StringUtils.isNotBlank(login)) {
			return entityManager.find(Utilisateur.class, login);
		}

		return null;
		
	}

}
