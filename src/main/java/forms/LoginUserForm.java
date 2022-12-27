package forms;

import java.util.HashMap;
import java.util.Map;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDaoImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginUserForm {
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";

	private static final String EMPTY_FIELD_ERROR_MESSAGE = "Vous devez renseigner ce champ";
	private static final String WRONG_PASSWORD_ERROR_MESSAGE = "";

	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private boolean status;
	private String statusMessage;
	private Utilisateur utilisateur;
	DaoFactory daoFactory = DaoFactory.getInstance();
	private UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(daoFactory);

	public LoginUserForm(HttpServletRequest request) {
		this.request = request;
		this.statusMessage = "Login ou mot de passse incorrect";
		this.status = false;
		this.erreurs = new HashMap<String, String>();
	}

	public boolean login() {
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);

		this.utilisateur = new Utilisateur("", "", login, password);
		validerChamps(CHAMP_LOGIN, CHAMP_PASSWORD);

		if (erreurs.isEmpty()) {
			validerPasswords(utilisateur);
			if (erreurs.isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute("isConnected", true);
				status = true;
				statusMessage = "Connexion effectuee avec succes";
			}

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

	private void validerPasswords(Utilisateur utilisateur) {
		String login = getParameter(CHAMP_LOGIN);
		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordSaved = "";
		Utilisateur utilisateur1 = utilisateurDao.getUserByLogin(login);
		if (utilisateur1 != null) {
			passwordSaved = utilisateur1.getPassword();
		}

		if (password != null && !password.equals(passwordSaved)) {
			erreurs.put(CHAMP_PASSWORD, WRONG_PASSWORD_ERROR_MESSAGE);

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
}
