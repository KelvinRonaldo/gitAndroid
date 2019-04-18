<?php

    $nome = $_POST['txt_nome'];
    $email = $_POST['txt_email'];
    $endereco = $_POST['txt_endereco'];
    $telefone = $_POST['txt_telefone'];
    $linkedin = $_POST['txt_linkedin'];
    $foto = $_POST['txt_foto'];

    $contatoArray = array(
        "nome" => $nome,
        "email" => $email,
        "endereco" => $endereco,
        "telefone" => $telefone,
        "linkedin" => $linkedin,
        "foto" => $foto
    );

    // json_encode = CONVERTE ARRAY PHP EM JSON
    $contatoJson = json_encode($contatoArray);

    // DEFINIR A URL DA API QUE SERÁ UTILIZADA
    $url = "http://localhost:8080/contatos";

    //ABRIR A CONEXÃO PARA A API
    $conexao = curl_init($url);

    // DEFINIR O MÉTODO DA REQUISIÇÃO HTTP PARA POST
    curl_setopt($conexao, CURLOPT_CUSTOMREQUEST, "POST");

    //DEFINIR O CONTEUDO DO BODY DA REQUISIÇÃO HTTP
    curl_setopt($conexao, CURLOPT_POSTFIELDS, $contatoJson);

    // DEFINIR SE ACEITAMOS O RETORNO
    curl_setopt($conexao, CURLOPT_RETURNTRANSFER, true);

    //DEFINIR ALGUNS HEADER NECESSARIOS PARA A REQUISICAO
    curl_setopt($conexao, CURLOPT_HTTPHEADER,
    array(
        'Content-Type: application/json',
        'Content-Length: '.strlen($contatoJson)
    ));

    $retornoJson = curl_exec($conexao);

    echo $retornoJson;
?>

<h4>Cadastro Efetuado com Sucesso!</h4>
<a href="index.php">Lista de Contato</a>