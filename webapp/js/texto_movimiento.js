const textElement = document.getElementById('text');
let position = 0; // Posición inicial
const speed = 2; // Velocidad del movimiento (ajusta según tus preferencias)

function moveText() {
  position += speed;
  textElement.style.transform = `translateX(${position}px)`;

  if (position > window.innerWidth) {
    position = -textElement.clientWidth; // Reinicia el texto fuera del viewport
  }

  requestAnimationFrame(moveText); // Llama a la función en el próximo ciclo de animación
}

moveText(); // Iniciar la animación
