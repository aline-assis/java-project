$(document).ready(function () {
    loadData()

    $('.modal').on('hidden.bs.modal', function (e) {
        $('.modal input[type=text], .modal input[type=time]').val('');
    })

    $('#newItem').on('click', function (e) {
        handleNewItem();
        $('.modal').modal('show')
    })
})

function loadData() {
    $('#dailyActivities').html('');
	$.get('peso', (data) => {
		data.list.forEach(item => {
	        const html = generateHtml(item);
	        $('#dailyActivities').append(html);
        });

	    $('.edit').unbind('click').on('click', async event => {
	        const btn = event.target;
	        btn.setAttribute('disabled', true)
	        await openEditModal(btn);
	        btn.removeAttribute('disabled')
	    })
	
	    $('.delete').unbind('click').on('click', async event => {
	        const btn = event.target;
	        btn.setAttribute('disabled', true)
	        await deleteActivitie(btn);
	        btn.removeAttribute('disabled')
	    })
	})
}

function generateHtml(data) {
    const html = `
    <div class="card mt-3" activity-id="${data.cdpeso}">
        <div class="card-body">
            <h5 class="card-title"> <i class="fas fa-weight fa-fw"></i> ${data.datapeso}</h5>
            <p class="card-text">
                <strong>Peso Registrado</strong> ${data.pesousuario}<br />
                <strong>Peso Ideal:</strong> ${data.pesoideal}
            </p>
            <div class="row">
                <div class="col-12 text-right">
                    <button class="btn btn-info edit" type="button"><i class="fas fa-edit"></i> Editar</button>
                    <button class="btn btn-danger delete" type="button"><i class="fas fa-trash-alt"></i> Excluir</button>
                </div>
            </div>
        </div>
    </div>
    `;
    return html
}

function openEditModal(element) {
    const card = element.closest('.card');
    const id = Number(card.getAttribute('activity-id'))
    
	$.get('peso', {id}, (data) => {
		$('#peso').val(data.list.pesousuario);
		$('#pesoIdeal').val(data.list.pesoideal);
    	$('.modal').modal('show');

	    $('#modalForm').unbind('submit').on('submit', async function (event) {
	  		event.preventDefault()
	        const form = event.target.closest('form');
	        const serializeArray = $(form).serializeArray();
	        handleEdit(id, serializeArray);
	    })
	})
}

function deleteActivitie(element) {
    const card = element.closest('.card');
    const id = Number(card.getAttribute('activity-id'))
    $.ajax({
    	url: urlEncoder('peso', {id}),
    	type: 'DELETE',
    	success: (data) => {
    		if(data.isDeleted){
    			loadData();
    		}
    	},
    	error: (response) => {
    		console.error(response);
    	}
    });
}

function objectifyForm(serializeArray, id = undefined) {//serialize data function
	let obj = {};
	serializeArray.forEach(item => {
		return obj[item.name] = item.value;
	});
	if (id) {
		obj = {...obj, id}
	}
	return obj;
}

function urlEncoder(url, data) {
	const urlParams = new URLSearchParams(data).toString()
	return url + '?' + urlParams;
}

function handleEdit(id, serializeArray) {
	const data = objectifyForm(serializeArray, id);

	$.ajax({
		url: urlEncoder('peso', data),
		method: 'PUT',
		success: async (response) => {
	       await loadData();
	       $('.modal').modal('hide');
		},
		error: (response) => {
			console.error(response);
		}
	});
}

function handleNewItem(){
    $('#modalForm').unbind('submit').on('submit', async function (event) {
        event.preventDefault()
        const form = event.target.closest('form');
        var serializeArray = objectifyForm($(form).serializeArray());
        serializeArray = {...serializeArray, userId: getUserId() }
        
     	$.ajax({
			url: 'peso',
		    type: 'POST',
		    dataType: 'json',
		    data: serializeArray,
		    success: function(response) {
		    	if(response.isValid) {
		    		 loadData()
		    	     $('.modal').modal('hide');
		    	}
		    	else {
		    		alert('Por favor insira dados validos!');
		    	}
		    }
		})
    })
}