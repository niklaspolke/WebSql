package vu.de.npolke.websql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vu.de.npolke.websql.model.QueryResult;

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
public class ExecutorDAO {

	public static final String DATASOURCE = "jdbc/hsqldbtest-db";
	public static final String TOMCAT_JNDI_PREFIX = "java:/comp/env/";

	private static final String SELECT_PREFIX = "SELECT";

	private static Logger logger;

	private DataSource datasource;

	public String connect(final String database) {
		datasource = null;
		String errorMessage = null;
		final String JNDI2DB = TOMCAT_JNDI_PREFIX + database;
		try {
			InitialContext context = new InitialContext();
			datasource = (DataSource) context.lookup(JNDI2DB);
		} catch (NamingException e) {
			logger.error("ERROR --- failed to lookup {} : {}", JNDI2DB, e.getMessage());
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}

	static {
		logger = LoggerFactory.getLogger(ExecutorDAO.class);
	}

	public boolean isSelect(final String sql) {
		StringTokenizer tokenizer = new StringTokenizer(sql, " ");
		return SELECT_PREFIX.equalsIgnoreCase(tokenizer.nextToken().trim());
	}

	public String executeUpdate(final String database, final String sql) {
		String result = "";
		String errorMessageFromConnect = connect(database);
		if (errorMessageFromConnect != null) {
			result += errorMessageFromConnect;
		} else {
			try (Connection connection = datasource.getConnection()) {
				PreparedStatement statement;
				statement = connection.prepareStatement(sql);
				result += statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				logger.debug("ERROR --- failed to execute \"{}\": {}", sql, e.getMessage());
				result += e.getMessage();
			}
		}
		return result;
	}

	public QueryResult executeQuery(final String database, final String sql) {
		QueryResult qResult = new QueryResult();
		String errorMessageFromConnect = connect(database);
		if (errorMessageFromConnect != null) {
			qResult.errorMessage = errorMessageFromConnect;
		} else {
			try (Connection connection = datasource.getConnection()) {
				PreparedStatement statement;
				statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
				ResultSetMetaData metaData = resultSet.getMetaData();
				qResult.headers = new ArrayList<String>();
				for (int columnIndex=1; columnIndex<=metaData.getColumnCount(); columnIndex++) {
					qResult.headers.add(metaData.getColumnLabel(columnIndex));
				}
				while (resultSet.next()) {
					List<String> singleResult = new ArrayList<String>();
					for (int columnIndex=1; columnIndex<=metaData.getColumnCount(); columnIndex++) {
						singleResult.add(resultSet.getString(columnIndex));
					}
					qResult.rows.add(singleResult);
				}
				connection.rollback();
			} catch (SQLException e) {
				logger.debug("ERROR --- failed to execute \"{}\": {}", sql, e.getMessage());
				qResult.errorMessage = e.getMessage();
			}
		}
		return qResult;
	}
}
