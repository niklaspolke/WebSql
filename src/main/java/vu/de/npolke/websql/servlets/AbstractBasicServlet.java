package vu.de.npolke.websql.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AbstractBasicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void setEncoding(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		setEncoding(request, response);

		HttpSession session = request.getSession();

		doGet(request, response, session);
	}

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response,
			final HttpSession session) throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		setEncoding(request, response);

		HttpSession session = request.getSession();

		doPost(request, response, session);
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response,
			final HttpSession session) throws ServletException, IOException {
		super.doPost(request, response);
	}
}
