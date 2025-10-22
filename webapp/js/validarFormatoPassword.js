/**
 * -Resumen: Controla que la contraseña esté bajo el formato indicado
 * -Autor: Jorge Jesús Chaparro Ibarra y Elio Jesús Jiménez Luque
 */

function validarFormatoPassword()
{
	var format	=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
	var errmessage = "";
	
	// Comprueba que la contraseña no tenga menos de 8 caracteres
	if(document.getElementById("password").value.length < 8)
	{
		errmessage += "Tu password debe tener mas de 8 caracteres\n"
	}
	// Comprueba que la contraseña tenga al menos una mayúscula, una minúscula, un digito y un carácter especial para ser valida
	if(!(document.getElementById("password").value.match(format)))
	{ 
		errmessage += "Tu password debe contener al menos una mayuscula, una minuscula, un digito y un caracter especial\n"
		
		// Comprueba que la contraseña no sea mayor a 14 caracteres
		if(document.getElementById("password").value.length > 14)
		{
			errmessage += "Tu password debe tener entre 8 y 14 caracteres\n"
		}		
	}
	
	if(errmessage != "")
	{
		alert(errmessage);
		return false;
	}
}