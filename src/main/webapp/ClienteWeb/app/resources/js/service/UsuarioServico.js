'use strict';

// URL do Webservice Rest
var URL_BASE = "http://10.0.3.9:9090/ControlePessoal/usuario/"

// Fábrica para interagir com o Webservice Rest e retornar os dados para o Controlador
App.factory('UsuarioService', function ($resource) {
      var data = $resource(URL_BASE+':cpf', {}, {
      update:{
          method:'PUT'
          },
      delete:{
          method: 'DELETE'    
        }
      });
      return data;
});
//{_cpf: '@cpf'}