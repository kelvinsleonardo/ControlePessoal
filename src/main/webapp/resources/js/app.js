'use strict';

var App = angular.module('appUsuario',[$routeProvider]);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'pagina/NovoUsuario.jsp',
            controller: 'NovoUsuario'
        }).
        otherwise({redirectTo: '/'});
}]);