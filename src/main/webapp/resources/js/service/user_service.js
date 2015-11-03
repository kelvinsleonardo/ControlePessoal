'use strict';

App.factory('ServicoUsuario', ['$http', '$q', function($http, $q){

    return {

        buscarTodosUsuarios: function() {
            return $http.get('http://localhost:9090/ControlePessoal/usuario/')
                .then(
                function(resposta){
                    return resposta.data;
                },
                function(erroResposta){
                    console.error('Erro ao buscar usuarios :(');
                    return $q.reject(erroResposta);
                }
            );
        },

        adicionarUsuario: function(usuario){
            return $http.post('http://localhost:9090/ControlePessoal/usuario/', usuario)
                .then(
                function(resposta){
                    return resposta.data;
                },
                function(erroResposta){
                    console.error('Erro ao criar usuario');
                    return $q.reject(erroResposta);
                }
            );
        },

        editarUsuario: function(usuario, cpf){
            return $http.put('http://localhost:9090/ControlePessoal/usuario/'+cpf, usuario)
                .then(
                function(resposta){
                    return resposta.data;
                },
                function(erroResposta){
                    console.error('Erro ao atualizar usuario');
                    return $q.reject(erroResposta);
                }
            );
        },

        removerUsuario: function(cpf){
            return $http.delete('http://localhost:9090/ControlePessoal/usuario/'+cpf)
                .then(
                function(resposta){
                    return resposta.data;
                },
                function(erroResposta){
                    console.error('Erro ao excluir usuario');
                    return $q.reject(erroResposta);
                }
            );
        }

    };

}]);