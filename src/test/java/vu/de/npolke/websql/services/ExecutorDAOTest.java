package vu.de.npolke.websql.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
public class ExecutorDAOTest {

	private static ExecutorDAO executorDAO;

	@Before
	public void setup() {
		executorDAO = new ExecutorDAO();
	}

	@Test
	public void isSelect_Select() {
		assertTrue(executorDAO.isSelect("select * from expense"));
	}

	@Test
	public void isSelect_SelectWithSpaces() {
		assertTrue(executorDAO.isSelect("   select  * from expense"));
	}

	@Test
	public void isSelect_SelectWithSpacesAndMixedUppercase() {
		assertTrue(executorDAO.isSelect("   Select  * from expense"));
	}

	@Test
	public void isSelect_Create() {
		assertFalse(executorDAO
				.isSelect("CREATE TABLE sequence (seq_name VARCHAR(40) PRIMARY KEY, seq_number INTEGER NOT NULL);"));
	}

	@Test
	public void isSelect_Insert() {
		assertFalse(executorDAO.isSelect("INSERT INTO sequence(seq_name, seq_number) VALUES ('ID_GENERATOR', 1000);"));
	}

	@Test
	public void isSelect_Update() {
		assertFalse(executorDAO.isSelect("update sequence set seq_number=1001;"));
	}
}
