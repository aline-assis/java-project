$(document).ready(function () {
    $('#registerForm').on('submit', function (event) {
        event.preventDefault()

        if ($('#register_password').val() !== $('#register_password_confirm').val()) {
            return alert('A senha e a confirmação de senha devem ser iguais.')
        }

        const serializeArray = $('#registerForm').serializeArray()

        const data = objectifyForm(serializeArray);

		$.post('user', {actionType: 'register', ...data}, (response) => {
	        alert('Cadastrado com sucesso!')
        	$('#registerModal').modal('hide')
		})
    	.fail((response) => {
    		console.error(response);
    		alert('Falha na comunicação com o servidor');
    	});
    })

    $('#login').on('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('login_email').value
        const password = document.getElementById('login_password').value
        if (!email || !password) {
        	alert('Dados inválidos');
        	return
        }
        const data = {actionType: 'login', email, password};
        $.post('user', data, (response) => {
        	if(response.userData){
        		const userData = JSON.stringify(response.userData);
        		window.localStorage.setItem('loggedUser', userData);
        		window.location.href = 'index.jsp';
        	} else {
        		alert('Usuário ou senha não encontrado');
        	}
    	})
    	.fail((response) => {
    		console.error(response);
    		alert('Falha na comunicação com o servidor');
    	});
    })
})

function objectifyForm(formArray) {//serialize data function
    var returnArray = {};
    for (var i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray
}