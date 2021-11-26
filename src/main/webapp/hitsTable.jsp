<%@ page import="model.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table align="center" id="pointsTable" class="input_part">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
        <th>Processing Time</th>
    </tr>
    <%--<% ArrayList<Point> points = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        try {
            ServletContext sc = request.getServletContext();
            points = (ArrayList<Point>) sc.getAttribute("points");
            for (Point point : points) {
                res
                        .append("<tr>")
                        .append("<td class=\"point_x\">").append(point.getX()).append("</td>")
                        .append("<td class=\"point_y\">").append(point.getY()).append("</td>")
                        .append("<td class=\"point_r\">").append(point.getR()).append("</td>")
                        .append("<td>").append(point.getResult()).append("</td>")
                        .append("<td>").append(point.getProcessingTime()).append(" sec").append("</td>")
                        .append("</tr>");
            }
        } catch (Exception ignored) {
        }%>--%>

    <% try { %>
    <% ServletContext sc = request.getServletContext(); %>
    <% ArrayList<Point> points = (ArrayList<Point>) sc.getAttribute("points"); %>
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

