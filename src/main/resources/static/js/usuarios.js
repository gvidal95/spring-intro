// Call the dataTables jQuery plugin
$(document).ready(function() {
//   alert('123');
   cargarUsuarios();
  $('#usuarios').DataTable();
    actualizarEmailTxtUsuario();
});

function actualizarEmailTxtUsuario(){
    document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}

async function cargarUsuarios(){
    const resp = await fetch('api/usuarios', {
      method: 'GET', // or 'PUT'
      headers: getHeaders()
    });

    const usuarios = await resp.json();

    let html = '';

    usuarios.map( usr => {
        html += `
            <tr>
                <td>${usr.id}</td>
                <td>${usr.nombre}</td>
                <td>${usr.email}</td>
                <td>${usr.telefono === null ? "No tiene" : usr.telefono}</td>
                <td>
                    <a href="#" onClick="eliminarUsuario(${usr.id})" class="btn btn-danger btn-circle btn-lg">
                        <i class="fas fa-trash"></i>
                    </a>
                </td>
            </tr>
        `
    } );


    document.querySelector("#usuarios tbody").outerHTML = html;

    console.log(usuarios)
}

function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token,
    }
}

async function eliminarUsuario(id){

    if(!confirm('¿Desea eliminar el usuario?')) return;

     const resp = await fetch(`api/usuarios/${id}`, {
          method: 'DELETE',
          headers: getHeaders
     });

     location.reload();

//      await resp.json();
}
