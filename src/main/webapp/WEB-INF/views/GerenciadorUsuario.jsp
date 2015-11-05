<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>Gerenciador de Usuarios</title>

    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body ng-app="appUsuario">
<div ng-controller="ControladorUsuario as controlador">


    <form ng-submit="controlador.enviar()" name="formUsuario">

        <label>CPF</label>
        <input type="text" ng-model="controlador.usuario.cpf" placeholder="CPF"/>
        <br>


        <label>NOME:</label>
        <input type="text" ng-model="controlador.usuario.nome" placeholder="Nome"/>
        <br>

        <label for="endereco">ENDERECO:</label>
        <input type="text" ng-model="controlador.usuario.endereco" placeholder="Endereço"/>
        <br>

        <label for="telefone">TELEFONE:</label>
        <input type="text" ng-model="controlador.usuario.telefone" name="telefone" placeholder="Telefone" />


        <input type="submit"  ng-disabled="formUsuario.$invalid" value="{{!controlador.usuario.cpf ? 'Adicionar' : 'Atualizar'}}" >
        <input type="submit"  ng-click="controlador.disableClick(1)" ng-disabled="formUsuario.$invalid" value="Atualizar">
        <button type="button" ng-click="controlador.resetarFormulario()"  ng-disabled="formUsuario.$pristine">Limpar Formulário</button>
        <br><br><br>

    </form>


    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Lista dos usuarios</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Endereco</th>
                    <th>Telefone</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="usu in controlador.usuarios">
                    <td><span ng-bind="usu.ID"></span></td>
                    <td><span ng-bind="usu.cpf"></span></td>
                    <td><span ng-bind="usu.nome"></span></td>
                    <td><span ng-bind="usu.endereco"></span></td>
                    <td><span ng-bind="usu.telefone"></span></td>
                    <td>
                        <button type="button" ng-click="controlador.editar(usu.cpf)">Edit</button>
                        <button type="button" ng-click="controlador.remover(usu.cpf)">Remove</button>
                    </td>
                </tr>

                <li><a href="NovoUsuario">Novo Teste</a></li>

                </tbody>
            </table>
        </div>
    </div>
</div>




<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="https://code.angularjs.org/1.4.4/angular-route.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script src="<c:url value='/resources/js/controller/user_controller.js' />"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>