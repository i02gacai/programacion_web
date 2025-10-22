/**
 * Funciones para evitar que se marquen todos los checkboxes. Cuando se marca uno se desmarcan los demas.
 */

function uncheckRol(checkbox) {
    const checkboxes = document.getElementsByName("rol");
    checkboxes.forEach(cb => {
        if (cb !== checkbox) {
            cb.checked = false;
        }
    });
}

function uncheckEstado(checkbox) {
    const checkboxes = document.getElementsByName("estado");
    checkboxes.forEach(cb => {
        if (cb !== checkbox) {
            cb.checked = false;
        }
    });
}

function uncheckTipo(checkbox) {
    const checkboxes = document.getElementsByName("tipo");
    checkboxes.forEach(cb => {
        if (cb !== checkbox) {
            cb.checked = false;
        }
    });
}

function uncheckDificultad(checkbox) {
    const checkboxes = document.getElementsByName("dificultad");
    checkboxes.forEach(cb => {
        if (cb !== checkbox) {
            cb.checked = false;
        }
    });
}
