package ensai.ProjetJavaEE.election.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException;
import ensai.ProjetJavaEE.utilisateurs.services.UtilisateurInvalideException.ErreurUtilisateur;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class VoteService {

    @Autowired
	EntityManager em;

    @Autowired
	private ClotureService clotureService;
	
    @Autowired
	private RechercherElectionService rechercherElection;


	@Transactional(propagation = Propagation.REQUIRED)
	public void voter(Utilisateur utilisateur,String titre, String vote) throws ElectionInvalideException, UtilisateurInvalideException{
        Election election = rechercherElection.rechercherParTitre(titre);
        int nbOui;
		int nbNon;
		if(election!=null){

			if(utilisateur!=null){

				if(!clotureService.isCloturee(election)){

					if(vote.equals("oui")){

						nbOui =election.getNbOui();
						nbOui+=1;
					}else{
						nbNon=election.getNbNon();
						nbNon+=1;
					}
				}else{
					throw new ElectionInvalideException(ErreurElection.ELECTION_TERMINEE);
				}
			}else{
               throw new UtilisateurInvalideException(ErreurUtilisateur.UTILISATEUR_OBLIGATOIRE);
			}
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		
		em.persist(election);
	}

}
