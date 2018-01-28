Añadida dependencia spring-data-rest
Desde Postman

#Obtener usuarios
GET http://localhost:8080/users

#Obtener usuario por id
GET http://localhost:8080/users/1

#CREAR usuario por id

POST http://localhost:8080/users
BODY --> raw --> json
{
  "name": "New User",
  "role": "User"
}

#OBTENER SOLO UN NUMERO DETERMINADO DE USUARIOS

GET http://localhost:8080/users?size=2
aparecen links a la paginacion
    "next": {
      "href": "http://localhost:8080/users?page=1&size=2"
    },
    "last": {
      "href": "http://localhost:8080/users?page=2&size=2"

      
HATEOAS (Hypermedia as Representation of Application State)


#ORDENAR
GET http://localhost:8080/users?sort=name,desc

#BUSCAR POR ROLE
http://localhost:8080/users/search/findByRole?role=Admin

#Eliminar usuario
DELETE http://localhost:8080/users/5
