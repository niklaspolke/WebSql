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

<form action="login" method="post">
    <fieldset>
        <legend>Login</legend>
        <table>
            <tbody>
                <tr>
                    <th class="label">
                        <label for="password">Password:</label>
                    </th>
                    <td>
                        <input
                            type="password"
                            name="password"
                            size="40"
                            maxlength="30"
                            title="login password"
                            placeholder="<password>"
                            required="required"
                            value=""
                            autofocus>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="Reset">
                    </td>
                    <td>
                        <input type="submit" value="Login">
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>

</body>
</html>
