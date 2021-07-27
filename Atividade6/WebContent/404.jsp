<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="template/head.jsp">
	<jsp:param name="title" value="404" />
</jsp:include>
<body>
	<div class="boxError">
		<div class="centeredBox">
			<div class=" text-center">
				<h1 class="errorCode">404 <i class="fa fa-search fa-fw"></i></h1>
			</div>
			<div class="text-center">
				<p class="errorMessage">Opa... parece que o arquivo que você está procurando não foi encontrado. :(</p>
			</div>
			<div class="text-center">
				<a class="btn btn-primary" href="index.jsp"><i class="fa fa-home fa-fw"></i> Clique aqui para ir para a página inicial</a>
			</div>
		</div>
	</div>
</body>
</html>