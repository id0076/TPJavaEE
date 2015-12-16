package ensai.ProjetJavaEE.utilisateurs.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.ProfilsUtilisateur;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;

@Service
public class ListeUtilisateurService {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Utilisateur> listeUtilisateur() {
		
		List<?> liste = entityManager.createQuery("SELECT u FROM Utilisateur u").getResultList();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		for(Object object : liste) {
			if(object instanceof Utilisateur) {
				listeUtilisateur.add((Utilisateur) object);
			}
		}
		
		return listeUtilisateur;
		
	}
	
	public List<Utilisateur> listeUtilisateurPrenom(String prenom) {
		
		Query req = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.prenom LIKE :prenom");
		req.setParameter("prenom", "%" + prenom + "%");
		
		List<?> liste = req.getResultList();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		for(Object object : liste) {
			if(object instanceof Utilisateur) {
				listeUtilisateur.add((Utilisateur) object);
			}
		}
		
		return listeUtilisateur;
		
	}
	
	public List<Utilisateur> listeUtilisateurNom(String nom) {
		
		Query req = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.nom LIKE :nom");
		req.setParameter("nom", "%" + nom + "%");
		
		List<?> liste = req.getResultList();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		for(Object object : liste) {
			if(object instanceof Utilisateur) {
				listeUtilisateur.add((Utilisateur) object);
			}
		}
		
		return listeUtilisateur;
		
	}
	
	public List<Utilisateur> listeUtilisateurVille(String ville) {
		
		Query req = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.ville.nom LIKE :ville");
		req.setParameter("ville", ville);
		
		List<?> liste = req.getResultList();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		for(Object object : liste) {
			if(object instanceof Utilisateur) {
				listeUtilisateur.add((Utilisateur) object);
			}
		}
		
		return listeUtilisateur;
		
	}
	
	public List<Utilisateur> listeUtilisateurProfil(ProfilsUtilisateur profil) {
		
		Query req = entityManager.createQuery("SELECT u FROM Utilisateur u");
		
		List<?> liste = req.getResultList();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		for(Object object : liste) {
			if(object instanceof Utilisateur) {
				Utilisateur utilisateur = (Utilisateur) object;
				if(utilisateur.getProfils().contains(profil)) {
					listeUtilisateur.add(utilisateur);
				}
			}
		}
		
		return listeUtilisateur;
		
	}

}
