<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notatnik</h1>
        <form action="save" method="post">
            <fieldset>
                <label for="noteId">Identyfikator notatki</label>
                <input name="noteId" id="noteId" value="${fn:substring(UUID.randomUUID(), 0, 10)}" readonly/>
            </fieldset>
            <fieldset>
                <label for="noteContent">Treść notatki</label>
                <textarea name="noteContent" id="noteContent"></textarea>
            </fieldset>
            <fieldset>
                <input name="noteType" type="radio" value="pv" id="privateNote" checked/>
                <label for="privateNote">Notatka prywatna</label>
                <input name="noteType" type="radio" value="pb" id="publicNote"/>
                <label for="publicNote">Notatka publiczna</label>
            </fieldset>
            <input type="submit" value="Zapisz"/>
        </form>

        <c:if test="${not pvurls.isEmpty() and pvurls ne null}">
            <h3>Dostępne notatki prywatne</h3>
            <ul>
                <c:forEach items="${pvurls.entrySet()}" var="url">
                    <li><a href="${url.getValue()}">Notatka o id ${url.getKey()}</a></li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not pburls.isEmpty() and pburls ne null}">
            <h3>Dostępne notatki publiczne</h3>
            <ul>
                <c:forEach items="${pburls.entrySet()}" var="url">
                    <li><a href="${url.getValue()}">Notatka o id ${url.getKey()}</a></li>
                </c:forEach>
            </ul>
        </c:if>
    </body>
</html>