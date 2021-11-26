<%@ page import="model.Points" %>
<%@ page import="model.Point" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table  align="center" id="pointsTable" class="input_part">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
        <th>Processing Time</th>
    </tr>
        <% Points points = new Points();
        StringBuilder res = new StringBuilder();
        try {
            ServletContext sc = request.getServletContext();
            points = (Points) sc.getAttribute("points");
         for (Point point : points.getPointsList()) {
            res
            .append("<tr>")
            .append("<td class=\"point_x\">").append(point.getX()).append("</td>")
            .append("<td class=\"point_y\">").append(point.getY()).append("</td>")
            .append("<td class=\"point_r\">").append(point.getR()).append("</td>")
            .append("<td>").append(point.getResult()).append("</td>")
            .append("<td>").append(point.getProcessingTime()).append(" sec").append("</td>")
            .append("</tr>");
        }
        } catch (Exception ignored){
        }%>
        <%=res%>
</table>
