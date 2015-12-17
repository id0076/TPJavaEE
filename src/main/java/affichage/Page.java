package affichage;

import ensai.ProjetJavaEE.utilisateurs.modele.Utilisateur;

public class Page {
	
	protected String page;

	public Page(Utilisateur utilisateur, String titre, String contenu) {
		
		this.page = "<html>"
				+ "<body>"
				+ "<table border=0>"
				+ "<tr>"
				+ "<td colspan=1 width=200>"
				+ "Utilisateur connecté:  "
				+ "</td>"
				+ "<td colspan=2>"
				+ "%UTILISATEUR"
				+ "</td>"
				+ "</tr>"
				+ "<tr><td colspan=3 height=50/></td>"
				+ "<tr>"
				+ "<td colspan=3 height=50>"
				+ "<b>%TITRE</b>"
				+ "</td>"
				+ "<tr>"
				+ "<td colspan=3 >"
				+ "%CONTENU"
				+ "</td>"
				+ "</tr>"
				+ "<tr><td colspan=3 height=50/></td>"
				+ "</table>"
				+ "<a href='/" + utilisateur.getLogin() + "/Utilisateurs/'>Retour aux outils</a>"
				+ "<br/>"
				+ "<a href='/connexion.html'>Déconnexion</a>"
				+ "</body"
				+ "</html>";
		
		this.page = page.replace("%UTILISATEUR", utilisateur.toHTML());
		this.page = page.replace("%TITRE", titre);
		this.page = page.replace("%CONTENU", contenu);
		
	}
	
	public String toHtml() {
		return page;
	}

}
