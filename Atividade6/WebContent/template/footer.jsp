<div id="footer">
    <div class="container-fluid text-center">
        <div class="row">
            <div class="col ${param.page == 'sports' ? 'active' : 'noactive'}">
                <!-- Exercícios que eu pratico -->
                <a href="sports.jsp">
                    <i class="fas fa-dumbbell fa-fw"></i>
                </a>
            </div>
            <div class="col ${param.page == 'charts' ? 'active' : 'noactive'}">
                <!-- Resumo do mês -->
                <a href="index.jsp">
                    <i class="fas fa-home fa-fw"></i>
                </a>
            </div>
            <div class="col ${param.page == 'weight' ? 'active' : 'noactive'}">
                <!-- Registro de peso -->
                <a href="weight.jsp">
                    <i class="fas fa-weight fa-fw"></i>
                </a>
            </div>
            <div class="col ${param.page == 'heart' ? 'active' : 'noactive'}">
                <!-- Registro de pressão Arterial -->
                <a href="heart.jsp">
                    <i class="fas fa-heart fa-fw"></i>
                </a>
            </div>
        </div>
    </div>
</div>