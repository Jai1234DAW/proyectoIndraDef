# proyectoIndraDef
Este proyecto fue propuesto por parte de MINSAIT como práctica a los alumnos de 1 Curso de DAM/DAW y consiste en el desarrollo de un portal para eventos sostenibles.

# Portal para la Gestión de Eventos Sostenibles

---

## 📝 1. Planteamiento del Proyecto

Este proyecto consiste en el desarrollo de un **portal web** para gestionar eventos relacionados con la **sostenibilidad**, como conferencias, talleres o actividades ecológicas.

El sistema contempla:

- **Eventos** con nombre, fecha, duración, ubicación (online o presencial), categoría, organizador, etc.
- **Usuarios** que son de forma general aquellos que acceden al portal con su nombre, correo y contraseña.
- **Organizadores** que crean y gestionan eventos.
- **Participantes** que se registran y se inscriben en eventos.
- **Inscripciones** como resultado de la participación de los participantes a los eventos.
- **Categorías** para clasificar eventos y facilitar búsquedas.
- **Ubicación y Modalidad** debido a que los eventos o bien pueden ser presenciales o vía online.

> El objetivo es automatizar y centralizar toda la gestión de eventos, desde su creación hasta la inscripción y cancelación por parte de usuarios, asegurando que cada actor en este sistema cumpla el rol que le corresponde.

---

##  2. Tareas Realizadas

### 🔸 2.1 Desarrollo Web (HTML + CSS)

- ✅ Página principal con presentación y carrusel de eventos.
- ✅ Listado de eventos con tabla informativa.
- ✅ Página de detalle de evento con información ampliada.
- ✅ Diseño con etiquetas semánticas HTML5.
- ✅ Hojas de estilo CSS organizadas.
- ✅ Páginas estáticas.

📷 *Vista previa:*

![Portal Principal](./portal_eventos_sostenibles.jpg)

---

### 🔸 2.2 Base de Datos

- Diagrama entidad-relación (ER) con 6 entidades principales.
- Script SQL para la creación física de la base de datos.
- Descripción detallada de entidades, atributos y restricciones.

📷 *Ejemplo de diseño E-R:*

![Diagrama ER](./doc/diagrama_ER.jpg)

---

### 🔸 2.3 Programación Java (POO)

- Desarrollo en **Java** siguiendo programación orientada a objetos.
- Modularidad mediante clases como:
  - `Usuario`, `Participante`, `Organizador`
  - `Evento`, `Inscripcion`, `Categoria`
- Uso de listas (`ArrayList`) para gestionar colecciones.
- Separación de lógica para claridad y reutilización.

📷 *Estructura del código en Java:*

![Estructura Código Java](./doc/java_estructura.jpg)

---

### 🔸 2.4 Sistemas Informáticos

- Instalación de **Windows 10 PRO** en una **máquina virtual**.
- Despliegue local del portal web usando un servidor web (por ejemplo, **XAMPP** o **WAMP**).

📷 *Evidencias:*

![Configuración de la máquina virtual](./doc/config_vm.jpg)  
![Instalación de Windows](./doc/instalacion_windows.jpg)

---

### 🔸 2.5 Control de Versiones con GitHub

- Repositorio con ramas bien definidas para cada etapa del proyecto.
- Gestión del código mediante commits descriptivos.
- Integración de imágenes de evidencia.

📷 *Grafo de ramas Git:*

![Grafo de ramas](./grafo_ramas_git.jpg)

---

## 🗂 3. Estructura del Repositorio
