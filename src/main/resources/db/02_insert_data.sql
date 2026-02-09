/*!40101 SET NAMES utf8mb4 */;

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO authors (name) 
VALUES ('Gabriel García Márquez')
      ,('J.K. Rowling')
      ,('George R.R. Martin');


INSERT INTO categories (name) 
VALUES ('Novela')
      ,('Fantasía')
	  ,('Ciencia Ficción');


INSERT INTO books (name, description, pages, author_id, category_id) 
VALUES ('Cien Años de Soledad', 'Historia de la familia Buendía en Macondo', 417, 1, 1)
      ,('Harry Potter y la Piedra Filosofal', 'El inicio de la saga de Harry Potter', 309, 2, 2)
	  ,('Juego de Tronos', 'Primera parte de la saga Canción de Hielo y Fuego', 694, 3, 2);
