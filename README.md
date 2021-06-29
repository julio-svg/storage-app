# Storage AP
Microservicios realizado con Spring Boot, Swagger 2 y Spring Fox. También se utilizan otras librerías útiles como mapstruct, lombok.

Microservicio Product
CRUD de productos

En cuanto al api, se quería realizar los siguientes endpoints:

---

> GET /products/{id} 

Parámetros:
> parameter: id. identificador producto.
---
> GET /v1/products

Parámetros:

> **ids**: String (Optional; default: null)
>
> **nombres**: String (Optional; default: null)
>
> **precios**: String (Optional; default: null)

Las operaciones de filtrado por un mismo parámetro son OR

---
> POST /v1/products

---

> PUT /products/{id}

parameter:
> id. identificador producto.

---

> DELTE /products/{id}

parameter:
> id. identificador producto.

---

###Tecnologías
Apache Maven 3.6.3

Java 11

---

###Servicios disponibles
Una vez lanzado el microservicio se podrá acceder al API accediendo a la siguiente URL:

local: http://localhost:8080/swagger-ui/

---

###Buenas prácticas
Siempre se realizará el contrato del API antes de implementar nada y deberá ser validado por el equipo. Para el contrato se tendrán en cuenta las siguientes consideraciones:

Recursos: Serán las entidades sobre las que actuamos. Las acciones sobre estas entidades se realizarán con los métodos HTTP. En inglés, minúsculas y camel-case. Siempre nombres y nunca acciones ni verbos. URIs cortas para mejorar posicionamiento.

Path parameters: Siempre seguidos de la entidad a la que hacen referencia. Nunca usar varios seguidos.

Query parameters: Sólo se usarán para los métodos GET que devuelvan una lista. Su propósito solo puede ser filtrar, paginar, expandir o similares.

Para la salida de los servicios se usará JSON, camelCase, y se incluirá siempre un objeto data que envolverá la salida de cualquier petición.

Se deben respetar los objetos de las diferentes capas y nunca saltárselos ni pasar el mismo objeto por varias capas.

Se debe mapear el objeto de salida y de entrada.

Todo lo que se diseñe debe tener responsabilidad única, estar lo más desacoplado posible y estar lo más preparado para ser reutilizable.