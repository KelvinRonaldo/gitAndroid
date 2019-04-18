<?php

    $url = "http://localhost:8080/contatos";
    $dadosJson = file_get_contents($url);
    // json_encode = CONVERTE JSON PHP EM ARRAY
    $dadosArray = json_decode($dadosJson, true);
    // echo phpinfo();


?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Acessando API Rest</title>
</head>
    <body>
        <header>
            <a href="cadastro.html">NOVO CONTATO</a>
        </header>
        <div>
            <h3>Lista de Contatos</h3>
            <table border="50">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                </tr>
                <?php
                    foreach($dadosArray as $contato){
                ?>
                <!-- <tr> 
                    <td><?php echo $contato->id ?></td> 
                    <td><?php echo $contato->nome ?></td> 
                    <td><?php echo $contato->email ?></td> 
                </tr> -->
                <tr> 
                    <td><?php echo $contato['id'] ?></td> 
                    <td><?php echo $contato['nome'] ?></td> 
                    <td><?php echo $contato['email'] ?></td> 
                </tr>
                <?php
                    }
                ?>
            </table>
        </div>
        <footer></footer>
    </body>
</html>