<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>${bundle.getString("buscar_videos")}</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${param.nome == naoencontado}">
                <script>alert("Video nao encontrado!");</script>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        <h1>${bundle.getString("buscar_videos")}:</h1>
        <form action="buscarvideo" method="POST">
            <input type="text" name="arquivo" value="" required>
            <input type="submit" name="enviar" value="buscar" />
        </form>
        <ul>
            <li>
                <video width="640" height="560" controls>"+
                <source src="uploads/${param.nome}.mp4" type="video/mp4">
            </li>
        </ul>
        <form style="text-align:center" action="listavideos" method="GET">
            <input type="submit" value="${bundle.getString("lista_de_videos")}">
        </form>
        <form style="text-align:center" action="uploadvideo" method="GET">
            <input type="submit" value="${bundle.getString("upload_videos")}">
        </form>
    </body>
</html>
