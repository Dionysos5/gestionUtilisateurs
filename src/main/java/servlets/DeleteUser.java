package servlets;

import java.io.IOException;

import dao.DaoFactory;
import dao.UtilisateurDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/delete")

public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	private UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(daoFactory);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		utilisateurDao.supprimer(id);
		response.sendRedirect("list");

	}

}
