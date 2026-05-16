# Microservicio de Pedidos (ms-pedidos)

Este microservicio gestiona el ciclo de vida de los pedidos de los clientes. Incluye lógica de negocio automatizada para el cálculo del valor total del pedido y el manejo del estado del mismo.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.5.14
* **Base de Datos:** PostgreSQL (Alojado en Neon Cloud)
* **Gestor de Dependencias:** Maven
* **Validación:** Hibernate Validator

## 🏗️ Lógica de Negocio Destacada
* **Cálculo Automático del Total:** Utiliza eventos del ciclo de vida de JPA (`@PrePersist`) para calcular el `total` multiplicando automáticamente la `cantidad` por el `precioUnitario` antes de guardar el registro en la base de datos.
* **Validación de Datos:** Restringe el ingreso de datos corruptos mediante validaciones estrictas (formato de correo electrónico real con `@Email`, cantidades estrictamente positivas con `@Min(1)`, etc.).
* **Manejo Global de Excepciones:** Respuestas centralizadas y estructuradas a través de un `GlobalExceptionHandler` para errores de validación (400) y recursos no encontrados (404).

## ⚙️ Variables de Enorno Necesarias
Para que el microservicio pueda conectarse a la base de datos relacional en la nube, se deben configurar las siguientes variables en el entorno de ejecución (ya sea en las variables del sistema en local o en el panel de Render):

| Variable | Descripción | Ejemplo de Valor |
| :--- | :--- | :--- |
| `SPRING_DATASOURCE_URL` | URL de conexión JDBC a PostgreSQL en Neon | `jdbc:postgresql://ep-cool-darkness-a5.us-east-2.aws.neon.tech/neondb` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la base de datos | `neondb_owner` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña asignada en Neon DB | `AbC123XyZ789` |

## 🚦 Endpoints Disponibles (API REST)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/pedidos` | Registra un pedido (Calcula total) | `201 Created` |
| **GET** | `/api/pedidos` | Lista el historial de pedidos | `200 OK` |
| **GET** | `/api/pedidos/{id}` | Busca un pedido específico | `200 OK` |
| **PATCH** | `/api/pedidos/{id}/estado` | Actualiza el estado de un pedido | `{"estado": "PAGADO"}` |
| **DELETE** | `/api/pedidos/{id}` | Elimina un pedido por ID | `204 No Content` |

### Ejemplo de Body para POST (PedidoRequestDTO):
```json
{
  "cliente": "Juan Perez",
  "correoCliente": "juan.perez@example.com",
  "productoId": 1,
  "nombreProducto": "Laptop Lenovo",
  "cantidad": 3,
  "precioUnitario": 150.00
}
```
## 💻 Instrucciones para Ejecutar en Local
1. Clonar el repositorio:
```bash
git clone https://github.com/bryanbot/ms-pedidos.git
cd ms-pedidos
```
2. Configurar las Variables de Entorno:
Asegúrese de exportar las variables mencionadas en la sección anterior o configurarlas en las propiedades de ejecución de su IDE (Eclipse / Intellij IDEA).
* `DB_URL`: URL de base de datos en Neon.
* `DB_USERNAME`: Usuario de base de datos en Neon.
* `DB_PASSWORD`: Contraseña de base de datos en Neon.
* `PORT`: En este caso se esta usando el puerto `8081`.
3. Compilar y construir el archivo ejecutable (.jar)
```bash
mvn clean install
```
4. Levantar el servicio
```bash
mvn spring-boot:run
```
