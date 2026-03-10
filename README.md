# 📚 LiterAlura - Catálogo de Libros

<p align="center">
  <img src="https://img.shields.io/badge/Status-Desafío_Completado-blue?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
</p>

## 📖 Sobre el Proyecto
**LiterAlura** es una aplicación de consola que permite gestionar un catálogo de libros interactuando con la API de **Gutendex**. El objetivo principal es ofrecer una herramienta donde los usuarios puedan buscar libros por título, registrar autores y filtrar información relevante de forma persistente.

---

## 🛠️ Funcionalidades del Desafío
* 🔍 **Búsqueda de libros por título:** Consulta directa a la API de Gutendex.
* 💾 **Persistencia de Datos:** Los libros y autores consultados se guardan automáticamente en una base de datos local.
* 👥 **Listar Autores:** Visualización de todos los autores registrados en la base de datos.
* 📅 **Filtro Temporal:** Listar autores vivos en un año específico.
* 🌐 **Estadísticas de Idioma:** Listar libros según el idioma seleccionado.

---

## 💻 Tecnologías y Herramientas
| Tecnología | Función |
| :--- | :--- |
| **Java 17** | Lenguaje de desarrollo. |
| **Spring Boot 3** | Framework para la gestión del proyecto. |
| **Spring Data JPA** | Manejo de la capa de persistencia (ORM). |
| **PostgreSQL** | Base de datos relacional. |
| **Jackson** | Deserialización de los datos JSON de la API. |
| **Maven** | Gestión de dependencias. |

---

## ⚙️ Configuración del Entorno
Para que el proyecto funcione correctamente, sigue estos pasos:

1. **Base de Datos:**
   * Crea una base de datos en PostgreSQL llamada `literalura`.
   * Configura tus credenciales en el archivo `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

2. **Ejecución:**
   * Clona el repositorio: `git clone https://github.com/tu-usuario/literalura-challenge.git`
   * Importa el proyecto en tu IDE favorito como un proyecto Maven.
   * Ejecuta la clase principal que contiene el método `main`.

---

## 📂 Estructura del Proyecto
* `model/`: Contiene las entidades JPA (`Libro`, `Autor`) y los Records para el mapeo del JSON.
* `repository/`: Interfaces que extienden de `JpaRepository`.
* `service/`: Clases encargadas del consumo de la API y la lógica de conversión de datos.
* `principal/`: Clase que gestiona el menú interactivo y la lógica de usuario.

---
