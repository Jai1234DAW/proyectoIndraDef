let posicion = 0;
function moverCarrusel(direccion) {
  const carrusel = document.getElementById('carrusel-inner');
  const items = carrusel.querySelectorAll('.evento');
  const total = items.length;

  posicion += direccion;
  if (posicion < 0) posicion = total - 1;
  if (posicion >= total) posicion = 0;

  carrusel.style.transform = `translateX(-${posicion * 100}%)`;
}