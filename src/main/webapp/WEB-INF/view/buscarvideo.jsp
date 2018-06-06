<%@page contentType="text/html" pageEncoding="utf-8"%>
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
            <input type="submit" name="enviar" value="buscar" />
        </form>
        <ul>
            <li>
                <video width="640" height="560" controls>"+
                <source src="uploads/${param.nome}.mp4" type="video/mp4">
            </li>
        </ul>
        <form action="listavideos" method="GET">
            <input type="submit" value="${bundle.getString("lista_de_videos")}">
        </form>
        <form action="uploadvideo" method="GET">
            <input type="submit" value="${bundle.getString("upload_videos")}">
        </form>
    </body>
</html>
