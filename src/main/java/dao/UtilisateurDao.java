package dao;

import java.util.ArrayList;

import beans.Utilisateur;

public interface UtilisateurDao {
	boolean ajouter(Utilisateur utilisateur);

	boolean modifier(Utilisateur utilisateur);

	boolean supprimer(int id);

	ArrayList<Utilisateur> lister();

	public Utilisateur get(int id);

	public Utilisateur getUserByLogin(String login);

}
