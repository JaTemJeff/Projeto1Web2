<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>${bundle.getString("buscar_videos")}</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <h1>${bundle.getString("buscar_videos")}:</h1>
        <form action="buscarvideo" method="POST">
            <input type="text" name="arquivo" value="" required>
            <input type="submit" name="enviar" value="${bundle.getString("mensagem_buscar")}" />
        </form>
        <script>alert(${naoencontrado});</script>
        <c:choose>
            <c:when test="${not empty naoencontrado}">
                <script>alert(${naoencontrado});</script>
            </c:when>
            <c:when test="${param.nome == null}">  
            </c:when>
            <c:otherwise>
                <p>${param.nome}</p>
                <ul>
                    <li>
                        <video width="640" height="560" controls>"+
                        <source src="uploads/${param.nome}.mp4" type="video/mp4">
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
        <form style="text-align:center" action="listavideos" method="GET">
            <input type="submit" value="${bundle.getString("lista_de_videos")}">
        </form>
        <form style="text-align:center" action="uploadvideo" method="GET">
            <input type="submit" value="${bundle.getString("upload_videos")}">
        </form>
    </body>
</html>
