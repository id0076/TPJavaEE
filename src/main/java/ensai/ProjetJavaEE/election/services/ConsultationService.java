package ensai.ProjetJavaEE.election.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ensai.ProjetJavaEE.election.modele.Election;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class ConsultationService {

	
	
	public void consulterResultats(Election election){
		
	}
}
