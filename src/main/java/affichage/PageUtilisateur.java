package affichage;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;

public class PageUtilisateur extends Page {
	
	String login;

	public PageUtilisateur(Utilisateur utilisateur) {		
		super(utilisateur, "Outils", "%CONTENU");
		this.login = utilisateur.getLogin();
		
		String contenuOutils = "<a href='/" + utilisateur.getLogin() + "/Utilisateurs/Consultation'>Consulter mon profil</a><br/>"
				+ "<form><input type='text' name='ConsultLogin' /><input type='button' value='Consulter' onClick='fonctionConsulter()'/></form>"
				+ "<FORM method=GET action=http://www.google.com/custom><INPUT TYPE=text name=q size=15 maxlength=255 value=''><INPUT type=submit name=sa VALUE='OK'></FORM> ";
		this.page = page.replace("%CONTENU", contenuOutils);
	}

}
