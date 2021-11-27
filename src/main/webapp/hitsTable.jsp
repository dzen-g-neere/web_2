<%@ page import="model.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table align="center" id="pointsTable" class="input_part">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
        <th>Processing Time</th>
    </tr>
    <% try { %>
    <% ServletContext sc = request.getServletContext(); %>
    <% List<Point> points = (List<Point>) sc.getAttribute("points"); %>
    <% for (Point point : points) { %>
    <tr>
        <td class="point_x"><%=point.getX()%></td>
        <td class="point_y"><%=point.getY()%></td>
        <td class="point_r"><%=point.getR()%></td>
        <td><%=point.getResult()%></td>
        <td><%=point.getProcessingTime()+" sec"%></td>
    </tr>
    <% } %>
    <% } catch (Exception ignored) {
    }%>
</table>

