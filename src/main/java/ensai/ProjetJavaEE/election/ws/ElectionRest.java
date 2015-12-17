package ensai.ProjetJavaEE.election.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.OuvertureService;
import ensai.ProjetJavaEE.notifications.services.NotificationsServices;
import ensai.ProjetJavaEE.utilisateurs.ws.UtilisateursRest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("")
@Slf4j
public class ElectionRest {

	@Autowired
	private OuvertureService ouvertureService;

	@Autowired
	private NotificationsServices notificationsServices;

	@RequestMapping(method = RequestMethod.PUT)
	public void creer(String titre,Election election){
		log.info("=====> Création l'élection {}: {}.", titre, election);

		election.setTitre(titre);
	    election.setNbNon(0);
	    election.setNbOui(0);

		try{
			ouvertureService.creer(election);
		}catch(Exception e){
		}
	}

}
