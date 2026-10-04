# 🚀 Backend - Sistema de Gestión de Productos (CRUD)

API REST desarrollada con **Spring Boot**, **Java 8** y **MySQL**, para el reto técnico para MiFact.

El proyecto incluye **validaciones**, **manejo global de excepciones**, **carga inicial de datos (Seeder)** y **documentación interactiva mediante Swagger/OpenAPI**.

---

## 🛠️ Tecnologías y Dependencias Principales

- ☕ **Java 8** - Lenguaje de programación base.
- 🌱 **Spring Boot 2.7.18** - Framework principal del backend.
- 🗄️ **Spring Data JPA / Hibernate** - Persistencia y gestión de datos relacionales.
- ✅ **Spring Boot Starter Validation** - Validación de DTOs mediante Bean Validation.
- 📖 **Springdoc OpenAPI / Swagger UI** - Documentación interactiva de la API.
- 🐬 **MySQL** - Sistema de gestión de base de datos.
- 🔌 **MySQL Connector/J** - Conector JDBC para MySQL.
- 🧩 **Lombok** - Reducción de código repetitivo (Boilerplate).
- 📦 **Maven** - Gestión de dependencias y construcción del proyecto.

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **JDK 8** o una versión compatible.
2. **Apache Maven**.
3. **MySQL Server**.
4. **MySQL Workbench** (opcional).
5. MySQL ejecutándose en el **puerto 3306**.

Puedes verificar las versiones instaladas con:

```bash
java -version
mvn -version
```
---

## ⚙️ Configuración y Puesta en Marcha

### 1. Crear la Base de Datos

Abre MySQL Workbench, MySQL CLI o cualquier cliente compatible y ejecuta:

```sql
CREATE DATABASE scm_product;
```
---
### 2. Configurar las Credenciales

Modifica el archivo:

```text
src/main/resources/application.yml
```
Configura las credenciales de tu base de datos local:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/scm_product?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: root
    password: tu_contrasena
```
---

### 3. Ejecutar el Proyecto

Desde la raíz del proyecto ejecuta:

```bash
mvn clean spring-boot:run
```

También puedes importar el proyecto en **IntelliJ IDEA** y ejecutar la clase principal que contiene el método `main`.

Una vez iniciada correctamente la aplicación, estará disponible en:

```text
http://localhost:1100
```

---

## 💡 Características Principales

### 🌱 Carga Inicial Automática (Seeder)

El proyecto incluye un inicializador mediante `CommandLineRunner`.

Al iniciar la aplicación:

1. Se verifica si existen registros en la base de datos.
2. Si la base de datos se encuentra vacía, se lee un archivo JSON incluido dentro del proyecto.
3. Los productos son cargados automáticamente en la base de datos.
4. Si ya existen registros, se evita realizar una carga duplicada.

Esto permite disponer de datos iniciales sin necesidad de insertar manualmente registros en MySQL.

---

### ✅ Validaciones 

Los DTOs utilizan **Bean Validation** para garantizar que los datos recibidos cumplan las reglas definidas.

Entre las validaciones utilizadas se encuentran:

- `@NotBlank`
- `@NotNull`
- `@Positive`
- `@Min`

Las validaciones cuentan con mensajes descriptivos para facilitar la identificación de errores en las peticiones.

---

### 🛡️ Manejo Global de Excepciones

El proyecto implementa un controlador centralizado mediante:

```java
RestControllerAdvice
```

Esto permite gestionar las excepciones de forma uniforme y devolver respuestas JSON estructuradas.

Entre los casos contemplados se encuentran:

- Errores de validación.
- Recursos no encontrados.
- Datos inválidos.
- Excepciones relacionadas con la lógica de negocio.
- Errores inesperados del servidor.

De esta manera, la API mantiene respuestas consistentes y códigos HTTP adecuados.

---

## 📖 Documentación de la API

El proyecto utiliza **Swagger UI / OpenAPI** para proporcionar una interfaz interactiva desde la cual puedes consultar y probar los endpoints disponibles.

Con la aplicación ejecutándose, accede a:

```text
http://localhost:1100/swagger-ui/index.html#/
```

Desde Swagger UI podrás:

- 📋 Consultar los endpoints disponibles.
- 🔍 Revisar parámetros y estructuras de las peticiones.
- 📤 Ejecutar solicitudes HTTP.
- 📥 Visualizar las respuestas de la API.
- 🧪 Probar el CRUD directamente desde el navegador.

---

## 🔄 Operaciones CRUD

La API permite realizar las principales operaciones sobre los productos:

| Método HTTP | Operación | Descripción |
|---|---|---|
| `GET` | Obtener | Consulta productos |
| `GET` | Obtener por ID | Consulta un producto específico |
| `POST` | Crear | Registra un nuevo producto |
| `PUT` | Actualizar | Actualiza un producto existente |
| `DELETE` | Eliminar | Elimina un producto |

Los endpoints exactos pueden consultarse desde la interfaz de **Swagger UI**.

---

## 📁 Estructura General del Proyecto

```text
src/
├── main/
│   ├── java/
│   │   └── ...
│   │
│   └── resources/
│       ├── application.yml
│       └── ...
│
└── test/
    └── ...
```

La aplicación sigue una estructura orientada a la separación de responsabilidades entre las diferentes capas del backend.

---

## 📮 Postman Collection

Para facilitar las pruebas de la API, se agregará una **Postman Collection** dentro del repositorio.

La colección permitirá importar directamente todos los endpoints del sistema en **Postman** y probar las operaciones CRUD sin necesidad de configurarlas manualmente.

La colección incluirá las principales operaciones:

- `GET` - Obtener todos los productos.
- `GET` - Obtener un producto por ID.
- `POST` - Crear un producto.
- `PUT` - Actualizar un producto.
- `DELETE` - Eliminar un producto.

### Importar la colección

Una vez descargado o clonado el proyecto:

1. Abrir **Postman**.
2. Seleccionar **Import**.
3. Seleccionar el archivo de la colección ubicado en el proyecto.
4. Importar la colección.
5. Ejecutar la aplicación Spring Boot.
6. Probar los endpoints directamente desde Postman.

---
## 🚀 Ejecución Rápida

```bash
# Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>

# Acceder al proyecto
cd <NOMBRE_DEL_PROYECTO>

# Crear la base de datos
# CREATE DATABASE scm_product;

# Ejecutar la aplicación
mvn clean spring-boot:run
```

Luego abre Swagger:

```text
http://localhost:1100/swagger-ui/index.html#/
```

---

## 👨‍💻 Autor

**Matias Paolo Criollo Vigo**

Backend Developer | Java | Spring Boot | REST APIs | MySQL
