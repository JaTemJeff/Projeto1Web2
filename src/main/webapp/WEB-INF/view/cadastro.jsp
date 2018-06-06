<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${bundle.getString("mensagem_cadastro")}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles.css" >
    </head>
    <body>
        <h1>${bundle.getString("mensagem_login")}</h1>
        <form id='login-form' action="cadastro" method='POST'>
            <label>${bundle.getString("mensagem_email")}</label>
            <input type="email" name="usuario" required>
            <label>${bundle.getString("mensagem_senha")}</label>
            <input type="password" name="senha" required>
            <input type="submit" value="${bundle.getString("mensagem_cadastro")}">
        </form>
        <form action="login" method="GET">
            <input type="submit" value="${bundle.getString("mensagem_voltar")}">
        </form>
    </body>
</html>
