<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="template/head.jsp">
	<jsp:param name="title" value="Bem-Vindo(a)!" />
	<jsp:param name="js" value="login.js" />
</jsp:include>

<body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h1 class="display-4">Bem vindo ao <i class="fas fa-heartbeat fa-fw"></i> Health Track!</h1>
                        <p class="lead">Este app ajuda você a lembrar de suas atividas diárias para cuidar da sua saúde.</p>
                        <p class="lead">
                            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
                                data-target="#registerModal">Quero me cadastrar
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h1>Já sou cadastrado</h1>
                <hr />
            </div>
            <div class="col-12">
                <form id="login">
                    <div class="form-group">
                        <label for="login_email">Email</label>
                        <input type="email" class="form-control" id="login_email" name="email" aria-describedby="emailHelp"
                            placeholder="Digite seu e-mail">
                        <small id="emailHelp" class="form-text text-muted">Nunca compartilhe seu acesso com
                            ninguém.</small>
                    </div>
                    <div class="form-group">
                        <label for="login_password">Senha</label>
                        <input type="password" class="form-control" id="login_password" name="password"
                            placeholder="Digite sua Senha">
                    </div>
                    <button type="submit" class="btn btn-success">Entrar!</button>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <form id="registerForm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cadastrar-se</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="register_fullName">Nome Completo</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                        </div>
                        <div class="form-group">
                            <label for="register_email">Email</label>
                            <input type="email" class="form-control" id="register_email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="register_birthdate">Data de Nascimento</label>
                            <input type="date" class="form-control" id="register_birthdate" name="birthdate" required>
                        </div>
                        <div class="form-group">
                            <label for="register_password">Senha</label>
                            <input type="password" class="form-control" id="register_password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="register_password_confirm">Repita a senha</label>
                            <input type="password" class="form-control" id="register_password_confirm" name="password_confirm" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Cadastrar</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>

</html>