## 📚 Challenge Literatura - Alura Latam + Oracle ONE

Proyecto desarrollado en Java con Spring como parte del Challenge de Literatura.  
Permite buscar libros en la API pública [Gutendex](https://gutendex.com/books), procesar los datos en formato JSON, y almacenarlos con **Spring Data JPA** en una base de datos PostgreSQL.
## GitHub
* *Challenge Literatura*:
 https://github.com/juan-carlos-condori/challengeLiteratura.git 
## Ejecución del programa


https://github.com/user-attachments/assets/f9d3b3d3-0580-4665-a2b4-5d3fa34d47c8


## 🛠 Tecnologías

- Java 17 o superior  
- Spring framework Data JPA  
- PostgreSQL  
- Maven

## 🎯 Funcionalidades

- Buscar libro por título (datos JSON desde Gutendex)
- Listar libros registrados
- Listar autores registrados
- Listar autores vivos en un determinado año
- Listar libros por idioma
## ⚙️ Configuración

1. Crear la base de datos en PostgreSQL, por ejemplo:
   ```
   CREATE DATABASE literatura;
   ```
   
2. Editar: application.properties:
   ```
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    
    spring.jpa.hibernate.ddl-auto=update
    
    spring.jpa.show-sql=true
    sprinc.jpa.format-sql=true
   ```
## Autor ✒️

* **Juan Carlos Condori** - *Trabajo Inicial* - [JuanCarlosCondori](https://github.com/juan-carlos-condori)
* **Alura-Latam** - *Challenger Literatura* - [Contribuyente](https://app.aluracursos.com/formacion-spring-framework-grupo8-one)

## Expresiones de Gratitud 🎁

¡Gracias, Alura Latam y ONE Oracle!

Quiero expresar mi más sincero agradecimiento a Alura Latam y al programa ONE (Oracle Next Education) por brindarme la oportunidad de crecer como desarrollador. Su compromiso con la educación y la tecnología ha sido fundamental en mi aprendizaje, proporcionándome no solo conocimientos técnicos, sino también una comunidad increíble llena de apoyo e inspiración.

Gracias por el esfuerzo, la dedicación y la calidad del contenido, que han sido clave para mi formación. Hoy me siento más preparado y motivado para seguir avanzando en el mundo del desarrollo.

¡Mil gracias por esta experiencia transformadora! 🚀🙌

---
👨‍💻 Autor
Desarrollado por: **Juan Condori** – **Programa Oracle ONE + Alura Latam**
