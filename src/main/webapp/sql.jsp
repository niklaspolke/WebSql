<%--
Copyright 2015 Niklas Polke

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
--%>
<%@page language="Java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/styles.css">
    <link rel="shortcut icon" href="favicon.ico"/>
    <title>WebSql</title>
</head>

<body>

<h1>WebSql</h1>

<form action="executesql" method="post">
    <fieldset>
        <legend>SQL to execute</legend>
        <table>
            <tbody>
                <tr>
                    <th class="label">
                        <label for="database">DATABASE</label>
                    </th>
                    <td>
                        <input
                            type="text"
                            name="database"
                            size="40"
                            maxlength="40"
                            title="database to send sql statement to"
                            placeholder="jdbc/hsqldbtest-db"
                            required="required"
                            value="${sessionScope.database}">
                    </td>
                </tr>
                <tr>
                    <th class="label">
                        <label for="sql">SQL</label>
                    </th>
                    <td>
                        <input
                            type="text"
                            name="sql"
                            size="100"
                            maxlength="100"
                            title="sql to execute"
                            placeholder="select * from table"
                            required="required"
                            value="${sessionScope.sql}"
                            autofocus>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="Reset">
                    </td>
                    <td>
                        <input type="submit" value="Execute SQL">
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>

<c:if test="${not empty sessionScope.message || not empty sessionScope.queryResult}">
<form action="noaction" method="post">
    <fieldset>
        <legend>Result</legend>
        <table>
            <tbody>
                <tr>
                    <th class="label">
                        <label for="resultdatabase">DATABASE</label>
                    </th>
                    <td>
                        <input
                            type="text"
                            name="resultdatabase"
                            size="40"
                            maxlength="40"
                            title="database in which sql was executed"
                            value="${sessionScope.database}"
                            disabled>
                    </td>
                </tr>
                <tr>
                    <th class="label">
                        <label for="resultsql">SQL</label>
                    </th>
                    <td>
                        <input
                            type="text"
                            name="resultsql"
                            size="100"
                            maxlength="100"
                            title="executed sql"
                            value="${sessionScope.sql}"
                            disabled>
                    </td>
                </tr>
                <tr>
                    <th class="label">
                        <label for="message">MESSAGE</label>
                    </th>
                    <td>
                        <input
                            type="text"
                            name="message"
                            size="100"
                            maxlength="100"
                            title="message of sql execution"
                            value="<c:out value="${sessionScope.message}"/>"
                            disabled>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.queryResult}"><tr>
                    <td colspan="2">
                        <table>
                            <tr>
                                <c:forEach var="resultHeader" items="${sessionScope.queryResult.headers}"><th>
                                    <c:out value="${resultHeader}"/>
                                </th></c:forEach>
                            </tr>
                            <c:forEach var="resultRow" items="${sessionScope.queryResult.rows}"><tr>
                                <c:forEach var="columnContent" items="${resultRow}"><td>
                                    <c:out value="${columnContent}"/>
                                </td></c:forEach>
                            </tr></c:forEach>
                        </table>
                    </td>
                </tr></c:if>
            </tbody>
        </table>
    </fieldset>
</form>
</c:if>

</body>
</html>
