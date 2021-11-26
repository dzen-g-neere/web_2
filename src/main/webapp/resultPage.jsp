<%--
  Created by IntelliJ IDEA.
  User: DZstd
  Date: 17.11.2021
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Check result</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>


<table class="resultsPage" align="center">
    <tr>
        <td>
            <p class="error">
                Произошла ошибка, нажмите на кнопку, чтобы перейти на главную страницу.
            </p>
        </td>
    </tr>
    <tr>
        <td>
            <form method="get" action="<%= request.getContextPath() %>/controllerServlet">
                <button id="button" type="submit">Нажмите, для перехода на рабочую страницу.</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="hitsTable.jsp" />
        </td>
    </tr>
</table>
</body>
</html>
