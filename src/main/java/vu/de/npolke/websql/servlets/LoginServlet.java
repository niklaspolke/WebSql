package vu.de.npolke.websql.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vu.de.npolke.websql.util.HashUtil;

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
@WebServlet("/login")
public class LoginServlet extends AbstractBasicServlet {

	private static final long serialVersionUID = 1L;

	private static final String FILE_CONFIG = "/password.cfg";
	private static final String KEY_PASSWORD = "password";

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws ServletException, IOException {

		final String password = request.getParameter("password");

		Properties properties = new Properties();
		InputStream inputStream = getClass().getResourceAsStream(FILE_CONFIG);
		properties.load(inputStream);

		if (properties.getProperty(KEY_PASSWORD).equals(HashUtil.toMD5(password))) {
			session.setAttribute("login", true);
			response.sendRedirect("sql.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
