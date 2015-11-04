<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal -->
<div class="modal fade" id="modalEditarUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>Editar Usuario</h4>
            </div>
            <div class="modal-body">
                <form ng-submit="controlador.enviar()">
                    <div class="panel-body">

                        <div class="form-group">
                            <input type="number" class="form-control" ng-model="controlador.usuario.cpf" readonly/>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="controlador.usuario.nome"/>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="controlador.usuario.endereco"/>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="controlador.usuario.telefone"/>
                        </div>


                    </div>
                    <div class="col-md-offset-7">

                        <button type="submit" class="btn btn-sm btn-success"  >
                            <span class="glyphicon glyphicon-ok"></span>
                            <strong>Atualizar</strong>
                        </button>


                        <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span>
                            <strong>Cancelar</strong>
                        </button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>