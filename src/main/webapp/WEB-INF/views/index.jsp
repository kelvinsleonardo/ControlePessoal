<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usuario</title>
</head>
<body>
  <center>
  <h1>PAINEL CONTROLE USUARIO</h1>
  <h6>Desenvolvido por Kelvin Santiago</h6>

      <form action="adicionar">
          <input type="number" name="cpf" id="idcpf">
          <input type="text" name="nome" id="idnome">
          <input type="submit" value="Salvar">
          <input type="button" onclick="javascript:window.location='usuario'" value="Atualizar">
      </form>

      <table id="tabelausuario" class="table table-responsive table-bordered table-hover table-striped">
          <thead>
          <tr class="table-info">
              <th>Matricula</th>
              <th>Nome</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="usuario" items="${listadeusuarios}" >
              <tr>
                <form action="remover">

                  <td>${usuario.cpf}</td>
                    <input type="hidden" name="cpf" value="${usuario.cpf}"/>

                  <td>${usuario.nome}</td>

                      <td>
                          <button type="submit">Excluir</button>
                      </td>
                </form>
              </tr>
          </c:forEach>
          </tbody>
      </table>


  </center>
</body>
</html>
