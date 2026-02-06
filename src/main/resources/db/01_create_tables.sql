CREATE TABLE authors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE books (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  pages INT,
  author_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  
  CONSTRAINT fk_books_author
    FOREIGN KEY (author_id)
    REFERENCES authors(id),

  CONSTRAINT fk_books_category
    FOREIGN KEY (category_id)
    REFERENCES categories(id)
);


/*CREATE TABLE Author (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL
);*/