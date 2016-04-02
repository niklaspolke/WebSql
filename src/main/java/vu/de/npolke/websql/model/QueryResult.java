package vu.de.npolke.websql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class QueryResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<String> headers;

	public List<List<String>> rows = new ArrayList<List<String>>();

	public String errorMessage;

	public List<String> getHeaders() {
		return headers;
	}

	public List<List<String>> getRows() {
		return rows;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
