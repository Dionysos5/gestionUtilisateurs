package forms;

import java.util.HashMap;
import java.util.Map;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserForm {
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWORD_BIS = "passwordBis";

	private static final String EMPTY_FIELD_ERROR_MESSAGE = "Vous devez renseigner ce champs";
	private static final String WRONG_PASSWORD_ERROR_MESSAGE = "Les mots de passe ne sont pas identiques";
	private static final String WRONG_LOGIN_ERROR_MESSAGE = "Ce login est deja utilise";

	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private boolean status;
	private String statusMessage;
	private Utilisateur utilisateur;

	private DaoFactory daoFactory = DaoFactory.getInstance();
	private UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();

	public AddUserForm(HttpServletRequest request) {
		this.request = request;
		this.statusMessage = "Echec de l'operation";
		this.status = false;
		this.erreurs = new HashMap<String, String>();
	}

	public boolean ajouter() {
		String nom = getParameter(CHAMP_NOM);
		String prenom = getParameter(CHAMP_PRENOM);
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);

		this.utilisateur = new Utilisateur(nom, prenom, login, password);
		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		validerLogin();
		validerPasswords();

		if (erreurs.isEmpty()) {
			status = true;
			statusMessage = "Ajout effectue avec succes";
			utilisateurDao.ajouter(utilisateur);

		} else {
			status = false;
		}
		return status;

	}

	public boolean modifier() {
		int id = Integer.parseInt(getParameter(CHAMP_ID));
		String nom = getParameter(CHAMP_NOM);
		String prenom = getParameter(CHAMP_PRENOM);
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);

		this.utilisateur = new Utilisateur(id, nom, prenom, login, password);
		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		validerPasswords();

		if (erreurs.isEmpty()) {
			status = true;
			statusMessage = "Ajout effectue avec succes";
			utilisateurDao.modifier(utilisateur);

		} else {
			status = false;
		}
		return status;

	}

	private String getParameter(String parameter) {
		String valeur = this.request.getParameter(parameter);
		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();
	}

	private void validerChamps(String... parameters) {

		for (String parameter : parameters) {
			if (getParameter(parameter) == null) {
				erreurs.put(parameter, EMPTY_FIELD_ERROR_MESSAGE);
			}
		}

	}

	private void validerPasswords() {

		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);

		if (password != null && !password.equals(passwordBis)) {
			erreurs.put(CHAMP_PASSWORD, WRONG_PASSWORD_ERROR_MESSAGE);
			erreurs.put(CHAMP_PASSWORD_BIS, WRONG_PASSWORD_ERROR_MESSAGE);

		}

	}

	private void validerLogin() {

		String login = this.getParameter(CHAMP_LOGIN);
		Utilisateur user = utilisateurDao.getUserByLogin(login);

		if (user != null) {
			erreurs.put(CHAMP_LOGIN, WRONG_LOGIN_ERROR_MESSAGE);

		}

	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public boolean getStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
