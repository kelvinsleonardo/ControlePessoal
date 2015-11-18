// Diretiva para validar CPF
App.directive('cpfValido', function (){ 
   return {
      require: 'ngModel',
      link: function(scope, elem, attr, model) {
          model.$parsers.unshift(function(value) {
              var valid = validarCPF(value);
              model.$setValidity('cpfValido', valid);
              return valid ? value : undefined;
          });
          model.$formatters.unshift(function(value) {
              model.$setValidity('cpfValido', validarCPF(value));
              return value;
          });
      }
   };
});


// Diretiva para converter em UPPERCASE
App.directive('uppercase', function() {
   return {
     require: 'ngModel',
     link: function(scope, element, attrs, modelCtrl) {
        var capitalize = function(inputValue) {
           if(inputValue == undefined) inputValue = '';
           var capitalized = inputValue.toUpperCase();
           if(capitalized !== inputValue) {
              modelCtrl.$setViewValue(capitalized);
              modelCtrl.$render();
            }         
            return capitalized;
         }
         modelCtrl.$parsers.push(capitalize);
         capitalize(scope[attrs.ngModel]);
     }
   };
});

// Diretiva para permitir apenas números
App.directive('onlynumbers', function () {  
            return {  
                restrict: 'A',  
                link: function (scope, elm, attrs, ctrl) {  
                    elm.on('keydown', function (event) {  
                        if (event.which == 64 || event.which == 16) {  
                            // to allow numbers  
                            return false;  
                        } else if (event.which >= 48 && event.which <= 57) {  
                            // to allow numbers  
                            return true;  
                        } else if (event.which >= 96 && event.which <= 105) {  
                            // to allow numpad number  
                            return true;  
                        } else if ([8, 9, 13, 27, 37, 38, 39, 40].indexOf(event.which) > -1) {  
                            // to allow backspace, enter, escape, arrows  
                            return true;  
                        } else {  
                            event.preventDefault();  
                            // to stop others  
                            return false;  
                        }  
                    });  
                }  
            }  
});

// Função de validar CPF
function validarCPF(cpf) {
    if( !cpf )
        return false;
    
    cpf = cpf.replace(/[^\d]+/g,'');
     if(cpf == '') return false;
     if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
        return false;
     
    add = 0;
    for (i=0; i < 9; i ++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
        return false;
     
    add = 0;
    for (i = 0; i < 10; i ++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
        return false;
    return true;
}