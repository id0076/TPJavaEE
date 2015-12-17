package ensai.ProjetJavaEE.election.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import ensai.ProjetJavaEE.election.services.ElectionInvalideException.ErreurElection;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class ClotureService {
	
	
	public boolean isCloturee(Election election) throws ElectionInvalideException{
		boolean cloturee=false;
		Date actuelle = new Date();
		
		if(election!=null){
			
			if(election.getFin().after(actuelle)){
				cloturee=true;
			}
		}else{
			throw new ElectionInvalideException(ErreurElection.ELECTION_OBLIGATOIRE);
		}
		return cloturee;
	}

}
