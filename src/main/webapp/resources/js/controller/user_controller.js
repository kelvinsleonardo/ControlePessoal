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
            function(d) {
                    self.usuarios = d;
            },
            function(erroResposta){
                console.error('Erro ao buscar usuarios');
            }
        );
    };

    self.adicionarUsuario = function(usuario){
        ServicoUsuario.criarUsuario(usuario)
            .then(
            self.buscarTodosUsuarios,
            function(errResponse){
                console.error('Error while creating usuario.');
            }
        );
    };

    self.editarUsuario = function(usuario, id){
        ServicoUsuario.editarUsuario(usuario, id)
            .then(
            self.buscarTodosUsuarios,
            function(errResponse){
                console.error('Error while updating usuario.');
            }
        );
    };

    self.removerusuario = function(cpf){
        ServicoUsuario.removerUsuario(cpf)
            .then(
            self.buscarTodosUsuarios,
            function(errResponse){
                console.error('Error while deleting usuario.');
            }
        );
    };

    self.buscarTodosUsuarios();

    self.submit = function() {
        if(self.usuario.id==null){
            console.log('Saving New usuario', self.usuario);
            self.createusuario(self.usuario);
        }else{
            self.updateusuario(self.usuario, self.usuario.id);
            console.log('usuario updated with id ', self.usuario.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.usuarios.length; i++){
            if(self.usuarios[i].id == id) {
                self.usuario = angular.copy(self.usuarios[i]);
                break;
            }
        }
    };

    self.remover = function(cpf){
        console.log('Usuario a ser deletado: ', cpf);
        for(var i = 0; i < self.usuarios.length; i++){//clean form if the usuario to be deleted is shown there.
            if(self.usuarios[i].cpf == cpf) {
                //self.reset();
                break;
            }
        }
        self.removerusuario(cpf);
    };


    self.reset = function(){
        self.usuario={cpf:null,nome:'',endereco:'',telefone:''};
        $scope.myForm.$setPristine(); //reset Form
    };

}]);