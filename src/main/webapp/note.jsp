<jsp:useBean id="note" scope="request" type="com.example.jointnotes.Note"/>
<%@ page import="com.example.jointnotes.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Twoja notatka</title>
    </head>
    <body>
        <h2>Twoja notatka od id ${note.id}</h2>
        <p>${note ne null ? note.content : "Brak notatki o takim ID"}</p>
        <p><a href="${pageContext.request.contextPath}/index.jsp" target="_self">Utwórz nową notatkę</a></p>
    </body>
</html>
