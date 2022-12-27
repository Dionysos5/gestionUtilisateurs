package servlets;

import java.io.IOException;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.AddUserForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/update")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_UPDATE_USER = "/WEB-INF/modifierUtilisateur.jsp";
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Utilisateur utilisateur = null;
		AddUserForm form = new AddUserForm(request);

		if (id != null && id.matches("[0-9]+")) {
			utilisateur = utilisateurDao.get(Integer.parseInt(id));
		}

		if (utilisateur != null) {
			form.setUtilisateur(utilisateur);
			request.setAttribute("form", form);

			getServletContext().getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AddUserForm form = new AddUserForm(request);
		request.setAttribute("form", form);

		if (form.modifier()) {
			response.sendRedirect("list");
		} else {
			getServletContext().getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);

		}

	}

}
