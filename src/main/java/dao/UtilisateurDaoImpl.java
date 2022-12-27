package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DaoFactory daoFactory;

	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public static final ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	@Override
	public boolean ajouter(Utilisateur utilisateur) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion
					.prepareStatement("INSERT INTO users (nom, prenom, login, password) VALUES (?, ?, ?, ?)");

			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean modifier(Utilisateur user) {

		Connection connexion = null;
		PreparedStatement statement;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("UPDATE users SET nom=?, prenom=?, login=?, password=? WHERE id=?");

			statement.setString(1, user.getNom());
			statement.setString(2, user.getPrenom());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getPassword());
			statement.setString(5, Integer.toString(user.getId()));
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean supprimer(int id) {

		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("DELETE FROM users WHERE id=?");

			statement.setString(1, Integer.toString(id));
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A user was deleted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Utilisateur get(int id) {
		Connection connexion = null;
		ResultSet result;

		try {
			connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			result = statement.executeQuery("SELECT * FROM users WHERE id = '" + Integer.toString(id) + "'");
			while (result.next()) {
				int id1 = Integer.parseInt(result.getString(1));
				String nom = result.getString(2);
				String prenom = result.getString(3);
				String login2 = result.getString("login");
				String password = result.getString("password");
				return new Utilisateur(id1, nom, prenom, login2, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Utilisateur getUserByLogin(String login) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet result;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			result = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "'");
			while (result.next()) {
				int id = Integer.parseInt(result.getString(1));
				String nom = result.getString(2);
				String prenom = result.getString(3);
				String login2 = result.getString("login");
				String password = result.getString("password");
				return new Utilisateur(id, nom, prenom, login2, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public ArrayList<Utilisateur> lister() {
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			result = statement.executeQuery("SELECT * FROM users");
			while (result.next()) {
				int id = result.getInt("id");
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				String login = result.getString("login");
				String password = result.getString("password");

				utilisateurs.add(new Utilisateur(id, nom, prenom, login, password));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateurs;
	}

}
