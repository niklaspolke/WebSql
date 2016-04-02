package vu.de.npolke.websql.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vu.de.npolke.websql.model.QueryResult;
import vu.de.npolke.websql.services.ExecutorDAO;

/**
 * Copyright 2015 Niklas Polke
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * @author Niklas Polke
 */
@WebServlet("/executesql")
public class ExecuteSqlServlet extends AbstractBasicServlet {

	private static final long serialVersionUID = 1L;

	private ExecutorDAO executorDAO = new ExecutorDAO();

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response,
			final HttpSession session) throws ServletException, IOException {
	}

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) throws ServletException, IOException {

		String sql = request.getParameter("sql");
		String database = request.getParameter("database");

		String message;
		QueryResult queryResult = null;
		if (executorDAO.isSelect(sql)) {
			queryResult = executorDAO.executeQuery(database, sql);
			message = queryResult.errorMessage;
		} else {
			message = executorDAO.executeUpdate(database, sql);
		}

		session.setAttribute("sql", sql);
		session.setAttribute("database", database);
		session.setAttribute("message", message);
		session.setAttribute("queryResult", queryResult);
		response.sendRedirect("sql.jsp");
	}
}
