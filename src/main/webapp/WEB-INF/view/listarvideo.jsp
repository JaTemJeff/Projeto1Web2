<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${bundle.getString("lista_de_videos")}</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
         <h1>${bundle.getString("lista_de_videos")}</h1>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:forEach items="${videos}" var="listVideos">
            <ul>
                 <li>
                     <video width="160" height="120" controls>
                         <source src="uploads/${videos}.mp4" type="video/mp4">
                     </video>
                     <h4>${listVideos}</h4>
                 </li>     
             </ul>
        </c:forEach>
    <form action="buscarvideo" method="GET">
        <input type="submit" value="${bundle.getString("buscar_videos")}">
    </form>
    <form action="uploadvideo" method="GET">
        <input type="submit" value="${bundle.getString("upload_videos")}">
    </form>   
    </body>
</html>
