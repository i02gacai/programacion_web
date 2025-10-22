/**
 * 
 */
function mostrarFormulario() {
            var formulario1 = document.getElementById("form1");
            var formulario2 = document.getElementById("form2");
			var formulario3 = document.getElementById("form3");
			var formularioContenedor = document.getElementById("formularioContenedor");
            var seleccion = document.getElementById("seleccionFormulario").value;

            formulario1.style.display = seleccion === "1" ? "block" : "none";
            formulario2.style.display = seleccion === "2" ? "block" : "none";
            formulario3.style.display = seleccion === "3" ? "block" : "none";
        }