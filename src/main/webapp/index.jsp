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
                            name="sql"
                            size="40"
                            maxlength="40"
                            title="sql to execute"
                            placeholder="select * from table"
                            required="required"
                            value="TEST-DB"
                            disabled>
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
                <tr>
                    <td colspan="2">
                        ${sessionScope.result}
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>

</body>
</html>
