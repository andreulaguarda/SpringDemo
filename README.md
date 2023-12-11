## Spring Boot

Proyecto Spring Boot con las dependencias / starters:
* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev Tools
* Springdoc - Swagger

Aplicación API REST con acceso a base de datos H2 para persistir la información.

El acceso se puede realizar desde Postman o desde el Navegador. También se puede testear a través de [Swagger](http://localhost:8080/doc/swagger-ui/index.html).

## Entidad Book
1. Book
2. BookRepository
3. BookController
   * Buscar todos los libros
   * Buscar un solo libro 
   * Crear un libro nuevo 
   * Actualizar un libro existente 
   * Borrar un libro 
   * Borrar todos los libros