package servlets;

import java.io.IOException;

import forms.LoginUserForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN_UTILISATEUR = "/WEB-INF/loginUtilisateur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher(VUE_LOGIN_UTILISATEUR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginUserForm form = new LoginUserForm(request);
		request.setAttribute("form", form);

		if (form.login()) {
			response.sendRedirect("list");
		} else {
			getServletContext().getRequestDispatcher(VUE_LOGIN_UTILISATEUR).forward(request, response);
		}
	}

}
