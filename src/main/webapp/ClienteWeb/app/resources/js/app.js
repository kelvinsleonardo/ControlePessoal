'use strict';

// Declarando Modulo com suas dependencias.
var App = angular.module('appUsuario',[
                        'ngRoute',
                        'ngResource'
                        ]);

// Configurando rotas 
App.config(function($routeProvider) {
    $routeProvider.
      when('/', {
       templateUrl: 'templates/ListarUsuario.html',
       controller: 'UsuarioController'
     }).
      when('/NovoUsuario', {
        templateUrl: 'templates/NovoUsuario.html',
        controller: 'UsuarioController'
      }).
      when('/:cpf/Editar', {
        templateUrl: 'templates/EditarUsuario.html',
        controller: 'UsuarioController',
        method: 'setDadosUsuarioSelecionado'
      }).
       otherwise({
       redirectTo: '/'
     });
});