// Call the dataTables jQuery plugin
$(document).ready(function() {
    // On ready
});

async function iniciarSesion(){

    let datos = {};

    datos.email = document.getElementById("txtEmail").value.trim();
    datos.password = document.getElementById("txtPassword").value.trim();

    const resp = await fetch('api/login', {
      method: 'POST', // or 'PUT'
      headers:{
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });

    const respJson = await resp.text();

    if(respJson !== 'FAIL'){
      localStorage.token = respJson;
      localStorage.email = datos.email;
      window.location.href = 'usuarios.html';
    }else{
        alert('las credenciales son incorrectas');
    }

}

