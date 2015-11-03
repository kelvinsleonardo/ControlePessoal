<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gerenciador de Usuarios</title>
</head>
<body ng-app="appUsuario">
<div ng-controller="ControladorUsuario as controlador">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Lista dos usuarios</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Endereco</th>
                    <th>Telefone</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="usu in controlador.usuarios">
                    <td><span ng-bind="usu.cpf"></span></td>
                    <td><span ng-bind="usu.nome"></span></td>
                    <td><span ng-bind="usu.endereco"></span></td>
                    <td><span ng-bind="usu.telefone"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="controlador.remover(usu.cpf)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script src="<c:url value='/resources/js/controller/user_controller.js' />"></script>
</body>
</html>