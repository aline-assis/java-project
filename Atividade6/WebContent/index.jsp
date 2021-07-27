<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="template/head.jsp">
	<jsp:param name="title" value="Gráficos" />
	<jsp:param name="js" value="main.js" />
	<jsp:param name="js" value="3rdParty/chart.min.js" />
	<jsp:param name="js" value="charts.js" />
</jsp:include>

<body>
	<jsp:include page="template/header.jsp" />

    <div id="app-content">
        <div class="container">
            <div class="row">
                <div class="col col-md-8 mx-md-auto mt-3 text-center">
                    <h4>
                        <i class="fas fa-chart-bar fa-fw"></i>
                        Seu histórico de peso
                    </h4>
                    <hr />
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-8 mx-md-auto">
                    <!-- lista -->
                    <div class="card">
                        <canvas id="myChart" width="400" height="400"></canvas>
                    </div>
                </div>
            </div>

        </div>
    </div>

	<jsp:include page="template/footer.jsp">
		<jsp:param name="page" value="charts" />
	</jsp:include>
</body>

</html>