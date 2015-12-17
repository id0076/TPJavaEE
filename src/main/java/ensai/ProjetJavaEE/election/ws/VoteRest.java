package ensai.ProjetJavaEE.election.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ensai.ProjetJavaEE.election.services.VoteService;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("")
@Slf4j
public class VoteRest {

	@Autowired
	private VoteService voteService;

	@Autowired
	private NotificationsServices notificationsServices;

	public void vote(Utilisateur utilisateur,String titre,String vote){
		log.info("=====> L'utilisateur a voté {}: {}.", utilisateur);

		try{
			voteService.voter(utilisateur,titre,vote);
		}catch(Exception e){
		}
	}

}
