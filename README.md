# Club Deportivo Montecarlo - Backend API 🏆⚽
## 1. Descripción
Este repositorio contiene el backend para el sistema de reserva de canchas del **Club Deportivo Montecarlo**. Es una API REST robusta diseñada para gestionar de manera integral el flujo de reserva de espacios deportivos. El sistema permite a los usuarios buscar horarios disponibles en tiempo real, realizar reservas, procesar pagos simulados con generación automática de comprobantes de pago en PDF y recibir notificaciones de soporte directamente en sus correos electrónicos.



## 2. Arquitectura del Sistema
El backend está construido bajo el patrón de diseño de **Arquitectura en Capas** de Spring Boot, lo que garantiza una separación clara de responsabilidades y facilita el mantenimiento:

* **Controladores (`controller`):** Exponen los endpoints de la API REST y manejan las peticiones HTTP de entrada.
* **DTOs (`dto`):** Objetos de transferencia de datos que desacoplan las entidades de la base de datos de las respuestas/peticiones de la API.
* **Servicios (`service`):** Capa donde reside toda la lógica de negocio (procesamiento de reservas, validación de disponibilidad y reglas del club).
* **Entidades (`entity`):** Clases que representan las tablas de la base de datos MySQL mediante JPA (`@Table`).
* **Repositorios (`repository`):** Capa de acceso a datos que interactúa con MySQL a través de Spring Data JPA.
* **Seguridad (`security` / `config`):** Filtros y configuraciones encargados de interceptar y validar los tokens JWT.
* **Utilidades (`util`):** Clases auxiliares, como el generador de reportes PDF.


## 3. Instalación y Configuración

### Requisitos Previos
* **Java:** JDK 21 instalado y configurado en el sistema.
* **Gestor de Proyectos:** Apache Maven 3.8 o superior.
* **Base de Datos:** MySQL Server activo.
* **Servidor de Correo:** Una cuenta de Gmail con "Contraseña de aplicación" activada para el servicio SMTP.

### Paso 1: Clonar el proyecto
```bash
# Clonar el repositorio y acceder a la carpeta raíz
git clone https://github.com/Rafiqui12345/montecarlo-backend.git
cd montecarlo-backend
```

### Paso 2: Configuración de Variables e Inicialización de BD
Crea una base de datos vacía en tu gestor de MySQL llamada montecarlo_db.

Abre el archivo src/main/resources/application.properties y configura las credenciales de tu base de datos local y tu cuenta de correo SMTP:
```bash
spring.application.name=montecarlo-backend

# Configuración MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/montecarlo_db
spring.datasource.username=TU_USUARIO_MYSQL  # Ej. root
spring.datasource.password=TU_CONTRASEÑA_MYSQL

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

# Configuración de Correo SMTP (Gmail)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=TU_CORREO_GMAIL@gmail.com
spring.mail.password=TU_CONTRASEÑA_DE_APLICACION_DE_GMAIL
```

### Paso 3: Compilar y Levantar el Proyecto
Una vez configuradas las credenciales del paso anterior, ejecuta los siguientes comandos en tu terminal para compilar el código de Java, descargar las dependencias necesarias de Maven, autogenerar las tablas en MySQL y levantar el servidor:
```bash
# Compilar el proyecto y descargar dependencias
./mvnw clean install

# Iniciar el backend con el Maven Wrapper
./mvnw spring-boot:run

# Backend corriendo en: http://localhost:8080
```

## 4. Backend APIs
| Módulo                     | Método | Endpoint                    | Descripción                                                           | Rol                         |
| -------------------------- | ------ | --------------------------- | --------------------------------------------------------------------- | --------------------------- |
| **Autenticación**          | POST   | `/auth/login`               | Inicia sesión y genera el token JWT.                                  | **Público**                 |
| **Usuarios**               | POST   | `/usuarios`                 | Registra un nuevo usuario en el sistema.                              | **Público**                 |
| Usuarios                   | GET    | `/usuarios`                 | Lista todos los usuarios registrados.                                 | **ADMIN**                   |
| Usuarios                   | GET    | `/usuarios/{id}`            | Obtiene la información de un usuario por su identificador.            | **ADMIN**                   |
| Usuarios                   | PUT    | `/usuarios/{id}`            | Actualiza la información de un usuario.                               | **ADMIN**                   |
| Usuarios                   | DELETE | `/usuarios/{id}`            | Elimina un usuario del sistema.                                       | **ADMIN**                   |
| **Canchas**                | GET    | `/canchas`                  | Lista todas las canchas disponibles.                                  | **Público, ADMIN, CLIENTE** |
| Canchas                    | GET    | `/canchas/{id}`             | Obtiene la información de una cancha por su identificador.            | **ADMIN**                   |
| Canchas                    | POST   | `/canchas`                  | Registra una nueva cancha.                                            | **ADMIN**                   |
| Canchas                    | PUT    | `/canchas/{id}`             | Actualiza la información de una cancha.                               | **ADMIN**                   |
| Canchas                    | DELETE | `/canchas/{id}`             | Elimina una cancha registrada.                                        | **ADMIN**                   |
| **Disponibilidad**         | GET    | `/disponibilidad`           | Consulta la disponibilidad de una cancha según la fecha seleccionada. | **CLIENTE**                 |
| **Reservas**               | POST   | `/reservas`                 | Registra una nueva reserva.                                           | **CLIENTE**                 |
| Reservas                   | GET    | `/reservas`                 | Lista todas las reservas registradas.                                 | **ADMIN**                   |
| Reservas                   | GET    | `/reservas/{id}`            | Obtiene la información de una reserva por su identificador.           | **ADMIN**                   |
| Reservas                   | PUT    | `/reservas/{id}`            | Actualiza la información de una reserva.                              | **ADMIN**                   |
| Reservas                   | DELETE | `/reservas/{id}`            | Elimina una reserva.                                                  | **ADMIN**                   |
| Reservas                   | PATCH  | `/reservas/{id}/estado`     | Actualiza el estado de una reserva.                                   | **ADMIN**                   |
| Reservas                   | GET    | `/reservas/mis-reservas`    | Lista las reservas realizadas por el usuario autenticado.             | **CLIENTE**                 |
| **Pagos**                  | POST   | `/pagos`                    | Procesa el pago de una reserva.                                       | **CLIENTE**                 |
| Pagos                      | GET    | `/pagos/mis-pagos`          | Lista los pagos realizados por el usuario autenticado.                | **CLIENTE**                 |
| **PDF**                    | GET    | `/pdf/{id}`                 | Descarga el comprobante de pago en formato PDF.                       | **CLIENTE**                 |
| **Consultas**              | POST   | `/consultas`                | Registra una nueva consulta.                                          | **CLIENTE**                 |
| Consultas                  | GET    | `/consultas`                | Lista todas las consultas registradas.                                | **ADMIN**                   |
| Consultas                  | GET    | `/consultas/{id}`           | Obtiene una consulta por su identificador.                            | **ADMIN**                   |
| Consultas                  | DELETE | `/consultas/{id}`           | Elimina una consulta.                                                 | **ADMIN**                   |
| Consultas                  | GET    | `/consultas/mis-consultas`  | Lista las consultas realizadas por el usuario autenticado.            | **CLIENTE**                 |
| Consultas                  | PATCH  | `/consultas/{id}/responder` | Responde una consulta realizada por un cliente.                       | **ADMIN**                   |
| **Dashboard**              | GET    | `/dashboard`                | Obtiene los indicadores del panel administrativo.                     | **ADMIN**                   |
| **Historial de Reservas**  | GET    | `/historial/{reservaId}`    | Consulta el historial de cambios de una reserva.                      | **ADMIN**                   |
| **Configuración del Club** | GET    | `/configuracion`            | Obtiene la configuración general del club.                            | **ADMIN**                   |
| Configuración del Club     | PUT    | `/configuracion`            | Actualiza la configuración general del club.                          | **ADMIN**                   |
| **Debug**                  | GET    | `/debug/smtp`               | Verifica la conexión con el servidor SMTP.                            | **ADMIN**                   |

### Pruebas de la API
Para verificar el funcionamiento de los servicios REST del backend se recomienda utilizar **Postman**. Los endpoints marcados como Público pueden consumirse sin autenticación. En cambio, los endpoints correspondientes a los roles ADMIN y CLIENTE requieren un token JWT válido.

Para obtener el token de autenticación, primero debe realizarse una solicitud al endpoint:
```bash
POST /auth/login
```

Una vez obtenido el token, este debe enviarse en el encabezado Authorization de cada solicitud protegida con el siguiente formato:
```bash
Authorization: Bearer <token_jwt>
```
De esta manera, es posible probar cada endpoint según los permisos asignados al rol autenticado.

## 5. Tecnologías Utilizadas
- **Lenguaje:** Java 21.
- **Framework principal:** Spring Boot 3.5.14.
- **Módulos de Spring:** Spring Web, Spring Security, Spring Mail, Spring Data JPA y Spring Validation.
- **Base de datos:** MySQL Server.
- **Generador de PDF:** OpenPDF (v2.0.3) para la generación de comprobantes de pago.
- **Seguridad:** JSON Web Tokens (JJWT v0.11.5) para la autenticación y autorización basada en tokens.
- **Librerías de utilidad:** Lombok para la reducción de código repetitivo.

## 6. Funcionalidades
- **Control de disponibilidad de canchas:** El sistema valida que una cancha no pueda recibir dos reservas diferentes en la misma fecha y rango horario, garantizando la disponibilidad correcta de las instalaciones.
- **Generación dinámica de comprobantes PDF:** Al procesar un pago, el sistema genera automáticamente un comprobante en formato PDF a partir de la información almacenada en la base de datos.
- **Notificaciones automáticas por correo electrónico:** El sistema envía automáticamente correos electrónicos a los clientes para remitir los comprobantes de pago y las respuestas a las consultas registradas.
- **Gestión del estado de las reservas:** Administra el ciclo de vida de las reservas mediante la actualización de sus estados (Pendiente, Confirmada y Cancelada), registrando cada cambio en un historial para facilitar el seguimiento y la auditoría.

## 7. Autenticación y Seguridad
La API implementa un mecanismo de autenticación basado en Spring Security y JSON Web Tokens (JWT) para proteger los recursos del sistema. Los usuarios autenticados reciben un token JWT que debe enviarse en cada solicitud a los endpoints protegidos mediante el encabezado Authorization.
El control de acceso se realiza mediante autorización basada en roles, permitiendo que cada usuario acceda únicamente a las funcionalidades correspondientes a sus permisos.

### Control de acceso por roles

El sistema define los siguientes roles:

### ROLE_CLIENTE
- Consultar la disponibilidad de las canchas según la fecha seleccionada.
- Registrar reservas de canchas.
- Realizar el pago de sus reservas.
- Descargar sus comprobantes de pago en formato PDF.
- Registrar consultas y visualizar las respuestas del administrador.
- Consultar el historial de sus reservas y pagos realizados.
### ROLE_ADMIN
- Administrar la información de las canchas (registrar, actualizar, consultar y eliminar).
- Gestionar los usuarios registrados en el sistema.
- Administrar la configuración general del club deportivo.
- Supervisar las reservas y actualizar su estado.
- Consultar el historial de cambios de las reservas.
- Responder las consultas realizadas por los clientes.
- Visualizar las estadísticas e indicadores del sistema mediante el módulo Dashboard.

## 8. Licencia
Este proyecto fue desarrollado con fines académicos como parte de un trabajo universitario. 
Su contenido se presenta únicamente con fines de aprendizaje, evaluación y portafolio profesional. 
El sistema fue diseñado tomando como referencia los procesos del Club Deportivo Montecarlo y no está destinado para su comercialización sin la autorización de sus autores.
