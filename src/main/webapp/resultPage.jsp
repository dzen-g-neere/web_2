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


<table class="page_table" align="center">

    <tr>
        <td>
            <p id="enter-text">RESULTS</p>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="hitsTable.jsp" />
        </td>
    </tr>
    <tr>
        <td>
            <form method="get" action="<%= request.getContextPath() %>/servlet">
                <button id="button" class="sliding-button" type="submit">Take me back.</button>
            </form>
        </td>
    </tr>
</table>

</body>
</html>
