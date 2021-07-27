<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="template/head.jsp">
	<jsp:param name="title" value="Horários de Pesagem" />
	<jsp:param name="js" value="main.js" />
	<jsp:param name="js" value="weight.js" />
</jsp:include>

<body>
	<jsp:include page="template/header.jsp" />

    <div id="app-content">
        <div class="container">
            <div class="row">
                <div class="col col-md-8 mx-md-auto mt-3 text-center">
                    <h4>
                        <i class="fas fa-weight fa-fw"></i>
                        Os seus horários de pesagem
                    </h4>
                    <hr />
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-2 offset-md-8 text-right">
                    <button type="button" class="btn btn-block btn-success" id="newItem"><i class="fas fa-plus fa-fw"></i> Adicionar</button>
                </div>
            </div>

            <div class="row">
                <div id="dailyActivities" class="col-12 col-md-8 mx-md-auto">
                    <!-- lista de exercÃ­cios -->
                </div>
            </div>

        </div>
    </div>

	<jsp:include page="template/footer.jsp">
		<jsp:param name="page" value="weight" />
	</jsp:include>

    <!-- modal -->
    <div class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <form id="modalForm" currentId="">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
						<div class="form-group row">
						    <label for="peso" class="col-sm-4 col-form-label">Peso</label>
						    <div class="col-sm-8">
						        <input type="text" class="form-control" id="peso" name="peso" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label for="pesoIdeal" class="col-sm-4 col-form-label">Peso Ideal</label>
						    <div class="col-sm-8">
						        <input type="text" class="form-control" id="pesoIdeal" name="pesoIdeal" required>
						    </div>
						</div>
                    </div>
                    <div class="modal-footer">
                        <button id="btnSave" type="submit" class="btn btn-success"><i class="fas fa-check fa-fw"></i>
                            Salvar</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><i
                                class="fas fa-window-close fa-fw"></i> Cancelar</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</body>

</html>