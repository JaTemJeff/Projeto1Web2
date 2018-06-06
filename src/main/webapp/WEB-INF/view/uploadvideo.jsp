<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${bundle.getString("upload_videos")}</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <h1>${bundle.getString("upload_videos")}</h1>
        <form action="uploadvideo" method="POST"
                            accept-charset="utf-8"
                            enctype="multipart/form-data">
            <input type="file" name="arquivo" value="" required />
            <input type="submit" name="enviar" value="${bundle.getString("enviar_videos")}" />
        </form>
        <form style="text-align:center" action="buscarvideo" method="GET">
            <input type="submit" value="${bundle.getString("buscar_videos")}">
        </form>
        <form style="text-align:center;" action="listavideos" method="GET">
            <input type="submit" value="${bundle.getString("lista_de_videos")}">
        </form>
    </body>
</html>