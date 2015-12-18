package ensai.ProjetJavaEE.notifications.services;

import org.springframework.stereotype.Service;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

@Service
public class NotificationsServices {
	File file = new File("voteApp.log");
	Logger voteApp = createLogger("voteApp", "voteApp.log");

	public void notifier(Action actU, Utilisateur u, String msg) {
		voteApp.info(actU + "; " + u.getLogin() + "; " + u.getEmail() + "; " + msg);
	}

	private Logger createLogger(String string, String file) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		PatternLayoutEncoder ple = new PatternLayoutEncoder();

		ple.setPattern("%date{dd MMM yyyy; HH:mm} ; %level; %msg%n");
		ple.setContext(lc);
		ple.start();

		FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
		fileAppender.setFile(file);
		fileAppender.setEncoder(ple);
		fileAppender.setContext(lc);
		fileAppender.start();

		Logger logger = (Logger) LoggerFactory.getLogger(string);
		logger.addAppender(fileAppender);
		logger.setLevel(Level.DEBUG);
		logger.setAdditive(false); /* set to true if root should log too */

		return logger;

	}

	public String log() {
		String res = new String("");

		try (BufferedReader entree = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
			String ligne;
			while ((ligne = entree.readLine()) != null) {
				res += ligne;
				res += "<br/>";
			}
		} catch (IOException e) {

		}
		return res;
	}

	public String logLogin(String login) {
		String res = new String("");

		try (BufferedReader entree = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
			String ligne;
			while ((ligne = entree.readLine()) != null) {
				String[] listeMot = ligne.split(";");
				if (listeMot[4].toLowerCase().contains(login.toLowerCase())) {
					res += ligne;
					res += "<br/>";
				}
			}
		} catch (IOException e) {

		}
		return res;
	}

	public String logAction(String action) {
		String res = new String("");

		try (BufferedReader entree = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
			String ligne;
			while ((ligne = entree.readLine()) != null) {
				String[] listeMot = ligne.split(";");
				if (listeMot[3].toLowerCase().contains(action.toLowerCase())) {
					res += ligne;
					res += "<br/>";
				}
			}
		} catch (IOException e) {

		}
		return res;
	}

	public enum Action {

		CREATION_U("Création d'un utilisateur"), CREATION_V("Création d'une ville"), LISTAGE_U(
				"Listage d'utilisateurs"), MODIFICATION_U("Modification d'un utilisateur"), RECHERCHE_U(
						"Recherche d'un utilisateur"), SUPPRESSION_U("Suppression d'un utilisateur"), RECHERCHE_E(
								"Recherche d'une élection"), CREATION_E("Création d'une élection"), CLOTURE_E(
										"Cloturation d'une élection"), MODIFICATION_E(
												"Modification d'une élection"), LISTAGE_E(
														"Listage d'élections"), RESULTAT_E(
																"Résultat d'une élection"), VOTE_E(
																		"Vote à une élection"), LOG(
																				"Affichage dans le log");

		@Getter
		public String message;

		private Action(String message) {
			this.message = message;
		}

	}
}
