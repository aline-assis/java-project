$(document).ready(async function () {
	$.get('charts', (data) => {
	    const pesoUsuario = data.list.map(item =>  item.pesousuario);
	    const pesoIdeal = data.list.map(item =>  item.pesoideal);
	    const labels = data.list.map(item => item.datapeso);
	    const datasets = [
	        {
	            label: 'Peso atual',
	            borderColor: 'rgba(142, 68, 173,1.0)',
	            backgroundColor: 'rgba(142, 68, 173,0.3)',
	            data: pesoUsuario
	        },
	        {
	            label: 'Peso Ideal',
	            borderColor: 'rgba(41, 128, 185,1.0)',
	            backgroundColor: 'rgba(41, 128, 185,0.3)',
	            data: pesoIdeal
	        },
	    ]
	
	    var ctx = document.getElementById('myChart').getContext('2d');
	    var myChart = new Chart(ctx, {
	        type: 'line',
	        data: {
	            labels,
	            datasets
	        },
	        options: {
	            scales: {
	                yAxes: [{
	                    ticks: {
	                        beginAtZero: true
	                    }
	                }]
	            }
	        }
	    });
    })
})