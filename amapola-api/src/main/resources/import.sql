INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_USUARIO');

INSERT INTO usuarios (username, contraseña, email, enabled, fecha_creacion) VALUES ('admin', '$2a$10$0.T4AKINeYT0pf5rMvIvoeYqJcAptoT4shwealwuOHYf4hNMZHpWy', 'admin12091995@gmail.com', true, NOW());

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 2);

INSERT INTO categorias (nombre) VALUES ('Te');
INSERT INTO categorias (nombre) VALUES ('Alfajores');
INSERT INTO categorias (nombre) VALUES ('Fríos y Congelados');
INSERT INTO categorias (nombre) VALUES ('Cereales');

INSERT INTO subcategorias (nombre, categoria_id) VALUES ('Hamburguesas', 3);

