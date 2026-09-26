USE Mercadito_Municipal;
GO
CREATE TABLE tiendas (
    id_tienda INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);
GO

ALTER TABLE productos
ADD id_tienda INT;
GO

ALTER TABLE productos
ADD CONSTRAINT fk_productos_tiendas
FOREIGN KEY (id_tienda)
REFERENCES tiendas(id_tienda);
GO