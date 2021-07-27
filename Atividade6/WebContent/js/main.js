$(document).ready(function(){
     const currentUser = localStorage.getItem('loggedUser')
  	 if(!currentUser){
        return window.location.href = 'login.jsp'
     }

     const firstName = JSON.parse(currentUser).nome.split(' ')[0]
     document.getElementById('userName').innerHTML = firstName;
})

function getUserId() {
	var currentUser = localStorage.getItem('loggedUser')
	currentUser = JSON.parse(currentUser);
	if (currentUser) {
		return currentUser.cdusuario;
	} else {
		alert('Não há dados de usuário');
		return window.location.href = 'login.jsp';
	}
}

function exit(){
    localStorage.removeItem('loggedUser');
    window.location.href = 'login.jsp'
}