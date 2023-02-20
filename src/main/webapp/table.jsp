<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tabelle</title>
</head>
<body>
<h1>Tabellen-Generator</h1>


<!-- Form für Inputs erstellen -->
<!-- Inputs bekommen namen, damit man später so darauf zugreifen kann -->
<form action="table.jsp" method="post">
    <label>Zeilen</label>
    <input name="zeile" type="number" max="16" required>

    <label>Spalten</label>
    <input name="spalte" type="number" max="16" required>

    <button type="submit">Submit</button>
</form>


<!-- Farbpalette erstellen-->
<c:set var='colours' value='${["#B2F9A2", "#CDF9A2", "#F3FF89", "#FFE889", "#FFC789", "#FF9589",
"#FB7A7C", "#FB7AAC", "#FB7AD9", "#EF7AFB", "#C27AFB", "#907AFB", "#7AA3FB", "#7ACCFB", "#A2F9EF", "#A2F9C0"]}'></c:set>


<!-- Tabelle erstellen -->
<!-- Begonnen wird mit Zeilen. Die Zeilen enthalten Spalten -->
<!-- Zeilen & Spalten beginnen jeweils bei 1 und enden bei der gewünschten Anzahl -->
<!-- varStatus = Index -->
<table style="border-collapse: collapse">
    <c:forEach begin="1" end="${param.zeile}" varStatus="zeilenIndex">
        <tr>
            <c:forEach begin="1" end="${param.spalte}" varStatus="spaltenIndex">
                <td style="border: 1px solid black" bgcolor="${colours[(zeilenIndex.index + spaltenIndex.index) % 16]}">
                    <!-- Ausgabe der Tabelle erfolgt hier -->
                    <c:out value="${zeilenIndex.index}"></c:out>, <c:out value="${spaltenIndex.index}"></c:out></td>
            </c:forEach>
        </tr>
    </c:forEach>
    <!-- Zeigt  User-Input an -->
    <c:out value="Tabellengröße ist: "></c:out>
    <c:out value="${param}"></c:out>
</table>
</body>
</html>