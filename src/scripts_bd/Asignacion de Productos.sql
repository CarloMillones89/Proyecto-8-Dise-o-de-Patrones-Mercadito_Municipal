USE Mercadito_Municipal;
GO

UPDATE productos
SET id_tienda = 1
WHERE id_producto IN (1, 2);
GO

UPDATE productos
SET id_tienda = 2
WHERE id_producto IN (3, 4);
GO

UPDATE productos
SET id_tienda = 3
WHERE id_producto = 5;
GO

UPDATE productos
SET id_tienda = 3
WHERE id_producto IN (6, 7, 8, 9, 10);
GO

UPDATE productos
SET id_tienda = 1
WHERE id_producto IN (11, 12, 13, 14, 15);
GO

SELECT *
FROM productos;