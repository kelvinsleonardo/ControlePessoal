'use strict';

/* Controlador responsável por fazer a ligação da camada de visualização, com os dados
 * da aplicação.
 */
App.controller('UsuarioController', 
                function($scope, $route, $routeParams , $filter, $location, $window,UsuarioService) {
    $scope.usuario={
                cpf:null,
                nome:'',
                endereco:'',
                telefone:''};
    
    // Buscando todos usuários cadastrados.
    $scope.usuarios = UsuarioService.query();
    
    
    // Adicionando novo usuario.
    $scope.adicionarUsuario = function(){
   
        console.log("----- Adicionando Usuario ----\n");
        $scope.imprimeNoConsoleUsuario();
        
        UsuarioService.save({
                        cpf:$scope.usuario.cpf,
                        nome:$scope.usuario.nome,
                        endereco:$scope.usuario.endereco,
                        telefone:$scope.usuario.telefone
        }).$promise.then(function(data) {
                swal("Opa :D", "O usuário foi adicionado com sucesso!", "success")
                console.log("Usuário cadastrado com sucesso!");
                $scope.reset();
                
        }).catch(function(response) {
                if(response.status == 409) {
                    swal("Ixi :(", "Este CPF já pertence a um usuário!", "error")
                    console.log("Erro 409 - CPF já cadastrado");
                    
                }else{
                   swal("Ocorreu um erro no servidor :(", 
                             "Ocorreu um erro no processamento do nosso servidor!", "error");
                    console.log("Erro - Ocorreu um erro no servidor");
                }
        });
    }
    
    // Removendo usuario.
    $scope.removerUsuario = function(cpf){
        
        swal({   
            title: "Você tem certeza disso?",   
            text: "Todos os dados do usuário de CPF "+cpf+" serão apagados!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Sim, quero remover!",
            cancelButtonText: "Cancelar",   
            closeOnConfirm: false 
        }, function(){
            console.log("----- Removendo Usuario ----\n");
            console.log("CPF: "+cpf);
            
            UsuarioService.delete({cpf: cpf}).
                $promise.then(function(data) {
                    for(var contador = 0; contador < $scope.usuarios.length; contador++){
                        if($scope.usuarios[contador].cpf == cpf) {
                           $scope.usuarios.splice(contador, 1);
                            break;
                        }
                    }
                    console.log("Usuário removido com sucesso.");
                    swal("Usuário Deletado!", 
                         "O usuário foi deletado com sucesso :D", 
                         "success");
                    
                }).catch(function(response) {
                    if(response.status == 404) {
                        swal("Usuário não encontrado :(", 
                             "O usuário não foi encontrado, possivelmente foi removido anteriormente!", 
                             "error");
                        console.log("Erro 404 - Usuário não encontrado");

                    }else{
                        swal("Ocorreu um erro no servidor :(", 
                             "Ocorreu um erro no processamento do nosso servidor!", "error");
                        console.log("Erro - Ocorreu um erro no servidor");
                }
            });  
        });
        
    }
    
    // Pegando dados do parametro route para preencher form editar.
    $scope.setDadosUsuarioSelecionado = function(){
        var cpf = $routeParams.cpf;
        $scope.usuario = UsuarioService.get({cpf:cpf});
    }
    
    // Atualizando usuario.
    $scope.updateUsuario = function(){
        
        console.log("----- Atualizando Usuario ----\n");
        $scope.imprimeNoConsoleUsuario();
        
        UsuarioService.update({
                        cpf:$scope.usuario.cpf},{
                        cpf:$scope.usuario.cpf,
                        nome:$scope.usuario.nome,
                        endereco:$scope.usuario.endereco,
                        telefone:$scope.usuario.telefone
        }).$promise.then(function(data) {
                swal("Opa :D", "O usuário foi atualizado com sucesso!", "success")
                console.log("Usuário atualizado com sucesso!");
                
        }).catch(function(response) {
                if(response.status == 404) {
                   swal("Usuário não encontrado :(", 
                        "O usuário não foi encontrado, possivelmente foi removido anteriormente!", 
                             "error");
                    console.log("Erro 404 - Usuário não encontrado");
                    
                }else{
                      swal("Ocorreu um erro no servidor :(", 
                             "Ocorreu um erro no processamento do nosso servidor!", "error");
                    console.log("Erro - Ocorreu um erro no servidor");
                }
        });
       
    }
    
    // Inicializar método caso não seja undefined.
    $scope.init = function(){
        if ($route.current.method !== undefined) {
          $scope[$route.current.method]();
        }
     };
    
    // Chamada da função init.
    $scope.init();
    
    // Função de redirecionamento para Home (Painel Usuário)
    $scope.irParaHome = function(){
        $location.path("/");
        $window.location.reload(true);
    }
    
    // Funcão para imprimir dados do usuário (toString)
    $scope.imprimeNoConsoleUsuario = function(){
          console.log("CPF: "+$scope.usuario.cpf+
                      ", Nome: "+$scope.usuario.nome+ 
                      ", Endereço: "+$scope.usuario.endereco+ 
                      ", Telefone: "+$scope.usuario.telefone
        );
    }
    
    // Função para resetar formulário
    $scope.reset = function() {
      $scope.usuario.cpf = "";
      $scope.usuario.nome = "";
      $scope.usuario.telefone = "";
      $scope.usuario.endereco = "";
      $scope.formAdicionar.$setPristine();
    }
    
});