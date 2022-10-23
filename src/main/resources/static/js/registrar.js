// Call the dataTables jQuery plugin
$(document).ready(function() {
    // On ready
});

async function registrarUsuario(){

    let datos = {};

    datos.nombre = document.getElementById("txtNombre").value.trim();
    datos.apellido = document.getElementById("txtApellido").value.trim();
    datos.email = document.getElementById("txtEmail").value.trim();
//    datos.telefono = document.getElementById("txtNombre").value;
    datos.password = document.getElementById("txtPassword").value.trim();

    let repetirPassword = document.getElementById("txtRepetirPassword").value.trim();

    if(repetirPassword !== datos.password) return alert("Las contraseñas no coinciden");

    const resp = await fetch('api/usuarios', {
      method: 'POST', // or 'PUT'
      headers:{
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });

    alert("La cuenta fue creada con éxito");
    window.location.href = 'login.html';

}

