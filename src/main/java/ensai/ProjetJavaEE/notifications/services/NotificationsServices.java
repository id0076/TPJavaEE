package ensai.ProjetJavaEE.notifications.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationsServices {

	public void notifier(String message) {
		log.info("[EVT]: " + message);
	}
	
}
