USE Mercadito_Municipal;
GO
CREATE OR ALTER PROCEDURE sp_registrar_producto
    @p_codigo_barras VARCHAR(50),
    @p_nombre VARCHAR(150),
    @p_descripcion VARCHAR(MAX),
    @p_precio_venta DECIMAL(10,2),
    @p_id_categoria INT,
    @p_id_tienda INT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO productos (
        codigo_barras,
        nombre,
        descripcion,
        precio_venta,
        stock_actual,
        id_categoria,
        id_tienda
    )
    VALUES (
        @p_codigo_barras,
        @p_nombre,
        @p_descripcion,
        @p_precio_venta,
        0,
        @p_id_categoria,
        @p_id_tienda
    );

    PRINT 'Producto registrado exitosamente.';
END;
GO