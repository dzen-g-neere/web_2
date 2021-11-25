<%--
  Created by IntelliJ IDEA.
  User: DZstd
  Date: 07.11.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dmitrii Zalevskii WebLab 2</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body onload="loadCanvas()">
<table class="page">

    <tr>
        <td>
            <table class="header">
                <tr>
                    <td>Вариант 12034</td>
                    <td>WEB-LAB-2</td>
                    <td>Дмитрий Залевский P3212</td>
                </tr>
            </table>
        </td>
    </tr>

    <tr>
        <td>
            <table class="interactive_part">
                <tr>
                    <td>
                        <canvas class="input_part" id="canvas"></canvas>
                    </td>
                    <td>
                        <form method="post" action="<%= request.getContextPath() %>/servlet">
                            <table>
                                <tr>
                                    <td class="input_part">
                                        <table>
                                            <tr>
                                                <td>
                                                    Введите параметр X
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="text" maxlength="8" name="x" id="paramX"
                                                           pattern="-?([0-4])([.,][0-9]+)?"
                                                           step="0.01"
                                                           title="Y should be float number in range (-5, 5)">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="input_part">
                                        <table>
                                            <tr>
                                                <td>
                                                    Введите параметр Y
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table>
                                                        <tr>
                                                            <th>-3</th>
                                                            <th>-2</th>
                                                            <th>-1</th>
                                                            <th>0</th>
                                                            <th>1</th>
                                                            <th>2</th>
                                                            <th>3</th>
                                                            <th>4</th>
                                                            <th>5</th>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="-3"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="-2"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="-1"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="0"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="1"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="2"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="3"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="4"></td>
                                                            <td><input type="radio" class="radio_button"
                                                                       name="y" value="5"></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="input_part">
                                        <table>
                                            <tr>
                                                <td>
                                                    Введите параметр R
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                <input class="r_button selected" onclick="r1_0()"
                                                                       type="button" value="1.0"
                                                                       id="rBut1_0">
                                                            </td>
                                                            <td>
                                                                <input class="r_button" onclick="r1_5()" type="button"
                                                                       value="1.5"
                                                                       id="rBut1_5">
                                                            </td>
                                                            <td>
                                                                <input class="r_button" onclick="r2_0()" type="button"
                                                                       value="2.0"
                                                                       id="rBut2_0">
                                                            </td>
                                                            <td>
                                                                <input class="r_button" onclick="r2_5()" type="button"
                                                                       value="2.5"
                                                                       id="rBut2_5">
                                                            </td>
                                                            <td>
                                                                <input class="r_button" onclick="r3_0()" type="button"
                                                                       value="3.0"
                                                                       id="rBut3_0">
                                                            </td>
                                                            <td><input id="valueR" name="r" type="hidden"
                                                                       value="1.0"></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="input_part">
                                        <input type="submit" value="Отправить" id="butSend">
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td class="input_part">
                                        <input type="button" id="clearButton" value="Очистить">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            <label class="error" id="errorX" hidden>
                Введите X (-5, 5)
            </label>
        </td>
    </tr>
    <tr>
        <td>
            <label class="error" id="errorY" hidden>
                Выберите Y
            </label>
        </td>
    </tr>
</table>
<jsp:include page="hitsTable.jsp"/>
</body>
<script src="FormManager.js"></script>
<script src="CanvasManager.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</html>
