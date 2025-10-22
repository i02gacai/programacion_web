/**
 * 
 */
function mostrarCampos() {
            var seleccion = document.getElementById("seleccionFormulario").value;
            var campoAdultos = document.getElementsByName("numero_adultos")[0];
            var campoNinos = document.getElementsByName("numero_ninos")[0];

            if (seleccion === "familiar") {
                campoAdultos.style.display = "block";
                campoNinos.style.display = "block";
                campoNinos.setAttribute('required', 'required');
                campoAdultos.setAttribute('required', 'required');
            } else if (seleccion === "adultos") {
                campoAdultos.style.display = "block";
                campoNinos.style.display = "none";
                campoAdultos.setAttribute('required', 'required');
                campoNinos.removeAttribute('required');
            }
            else if (seleccion === "infantil") {
                campoAdultos.style.display = "none";
                campoNinos.style.display = "block";
                campoNinos.setAttribute('required', 'required');
                campoAdultos.removeAttribute('required');
            }
        }