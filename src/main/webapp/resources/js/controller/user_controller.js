'use strict';

App.controller('ControladorUsuario', ['$scope', 'ServicoUsuario', function($scope, ServicoUsuario) {
    var self = this;
    self.usuario={
                cpf:null,
                nome:'',
                endereco:'',
                telefone:''};
    self.usuarios=[];

    self.buscarTodosUsuarios = function(){
        ServicoUsuario.buscarTodosUsuarios()
            .then(
            function(data) {
                    self.usuarios = data;
            },
            function(erroResposta){
                console.error('Erro ao buscar usuário');
            }
        );
    };

    self.adicionarUsuario = function(usuario){
        ServicoUsuario.adicionarUsuario(usuario)
            .then(
            self.buscarTodosUsuarios,
            function(erroResposta){
                console.error('Erro ao adicionar um novo usuário.');
            }
        );
    };

    self.editarUsuario = function(cpf, usuario){
        ServicoUsuario.editarUsuario(cpf, usuario)
            .then(
            self.buscarTodosUsuarios,
            function(erroResposta){
                console.error('Erro ao atualizar usuario no controlador.');
            }
        );
    };

    self.removerUsuario = function(cpf){
        ServicoUsuario.removerUsuario(cpf)
            .then(
            self.buscarTodosUsuarios,
            function(errResponse){
                console.error('Erro ao deletar usuario.');
            }
        );
    };

    self.buscarTodosUsuarios();

    self.enviar = function() {
        if(self.usuario.cpf == null){
            console.log('Salvando novo usuário', self.usuario);
            self.adicionarUsuario(self.usuario);
        }else{
            self.editarUsuario(self.usuario.cpf, self.usuario);
            console.log('Usuário editado com cpf: ', self.usuario.cpf);
        }
        self.resetarFormulario();
    };

    self.editar = function(cpf){
        console.log('ID a ser editado:', cpf);
        for(var contador = 0; contador < self.usuarios.length; contador++){
            if(self.usuarios[contador].cpf == cpf) {
                self.usuario = angular.copy(self.usuarios[contador]);
                break;
            }
        }
        $scope.showModal = true;
    };

    self.remover = function(cpf){
        console.log('Usuario a ser deletado: ', cpf);
        for(var i = 0; i < self.usuarios.length; i++){//clean form if the usuario to be deleted is shown there.
            if(self.usuarios[i].cpf == cpf) {
                self.resetarFormulario();
                break;
            }
        }
        self.removerUsuario(cpf);
    };


    self.resetarFormulario = function(){
        self.usuario={
            cpf:null,
            nome:'',
            endereco:'',
            telefone:''
        };
        $scope.formUsuario.$setPristine();
    };




}]);