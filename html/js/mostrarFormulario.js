
 function mostrarFormulario() {
      document.getElementById('formulario-inscripcion').style.display = 'block';
      document.getElementById('mensaje-cancelacion').style.display = 'none';
    }

    function cancelarInscripcion() {
      document.getElementById('formulario-inscripcion').style.display = 'none';
      document.getElementById('mensaje-cancelacion').style.display = 'block';
    }
