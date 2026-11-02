USE Mercadito_Municipal;
GO

-- =============================================
-- 1. CREAR TABLA PEDIDO
-- =============================================

IF OBJECT_ID('dbo.Pedido', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.Pedido (
        id_pedido INT IDENTITY(1,1) PRIMARY KEY,
        nombre_cliente VARCHAR(100) NOT NULL,
        dni_cliente VARCHAR(8) NOT NULL,
        producto VARCHAR(120) NOT NULL,
        cantidad INT NOT NULL,
        precio_unitario DECIMAL(10,2) NOT NULL,
        total DECIMAL(10,2) NOT NULL,
        fecha_pedido DATETIME NOT NULL DEFAULT GETDATE(),
        estado VARCHAR(20) NOT NULL DEFAULT 'Pendiente',
        tipo_pedido VARCHAR(20) NOT NULL DEFAULT 'TIENDA',
        direccion_entrega VARCHAR(200) NULL,
        costo_envio DECIMAL(10,2) NOT NULL DEFAULT 0,

        CONSTRAINT chk_pedido_cantidad
            CHECK (cantidad > 0),

        CONSTRAINT chk_pedido_precio
            CHECK (precio_unitario > 0),

        CONSTRAINT chk_pedido_total
            CHECK (total > 0),

        CONSTRAINT chk_pedido_dni
            CHECK (LEN(dni_cliente) = 8),

        CONSTRAINT chk_pedido_tipo
            CHECK (tipo_pedido IN ('TIENDA', 'DELIVERY')),

        CONSTRAINT chk_pedido_envio
            CHECK (costo_envio >= 0)
    );
END;
GO

-- =============================================
-- 2. AGREGAR COLUMNAS SI LA TABLA YA EXISTIA
-- =============================================

IF COL_LENGTH('dbo.Pedido', 'tipo_pedido') IS NULL
    ALTER TABLE dbo.Pedido
    ADD tipo_pedido VARCHAR(20) NOT NULL
        CONSTRAINT DF_Pedido_tipo DEFAULT 'TIENDA';
GO

IF COL_LENGTH('dbo.Pedido', 'direccion_entrega') IS NULL
    ALTER TABLE dbo.Pedido
    ADD direccion_entrega VARCHAR(200) NULL;
GO

IF COL_LENGTH('dbo.Pedido', 'costo_envio') IS NULL
    ALTER TABLE dbo.Pedido
    ADD costo_envio DECIMAL(10,2) NOT NULL
        CONSTRAINT DF_Pedido_envio DEFAULT 0;
GO

-- =============================================
-- 3. REGISTRAR PEDIDO
-- =============================================

CREATE OR ALTER PROCEDURE dbo.sp_registrar_pedido
    @nombre_cliente VARCHAR(100),
    @dni_cliente VARCHAR(8),
    @producto VARCHAR(120),
    @cantidad INT,
    @precio_unitario DECIMAL(10,2),
    @tipo_pedido VARCHAR(20),
    @direccion_entrega VARCHAR(200) = NULL,
    @costo_envio DECIMAL(10,2) = 0
AS
BEGIN
    SET NOCOUNT ON;

    SET @tipo_pedido = UPPER(LTRIM(RTRIM(@tipo_pedido)));

    IF LEN(@dni_cliente) <> 8
        THROW 50001, 'El DNI debe tener 8 caracteres.', 1;

    IF @cantidad <= 0
        THROW 50002, 'La cantidad debe ser mayor que cero.', 1;

    IF @precio_unitario <= 0
        THROW 50003, 'El precio debe ser mayor que cero.', 1;

    IF @costo_envio < 0
        THROW 50004, 'El costo de envio no puede ser negativo.', 1;

    IF @tipo_pedido NOT IN ('TIENDA', 'DELIVERY')
        THROW 50005, 'Tipo de pedido no valido.', 1;

    IF @tipo_pedido = 'DELIVERY'
       AND (@direccion_entrega IS NULL
            OR LTRIM(RTRIM(@direccion_entrega)) = '')
        THROW 50006, 'El delivery necesita una direccion.', 1;

    IF @tipo_pedido = 'TIENDA'
    BEGIN
        SET @direccion_entrega = NULL;
        SET @costo_envio = 0;
    END;

    DECLARE @total DECIMAL(10,2);

    SET @total =
        (@cantidad * @precio_unitario) + @costo_envio;

    INSERT INTO dbo.Pedido (
        nombre_cliente,
        dni_cliente,
        producto,
        cantidad,
        precio_unitario,
        total,
        estado,
        tipo_pedido,
        direccion_entrega,
        costo_envio
    )
    VALUES (
        @nombre_cliente,
        @dni_cliente,
        @producto,
        @cantidad,
        @precio_unitario,
        @total,
        'Pendiente',
        @tipo_pedido,
        @direccion_entrega,
        @costo_envio
    );

    SELECT CAST(SCOPE_IDENTITY() AS INT) AS id_pedido;
END;
GO

-- =============================================
-- 4. CONSULTAR TODOS LOS PEDIDOS
-- =============================================

CREATE OR ALTER PROCEDURE dbo.sp_consultar_pedidos
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        id_pedido,
        nombre_cliente,
        dni_cliente,
        producto,
        cantidad,
        precio_unitario,
        total,
        fecha_pedido,
        estado,
        tipo_pedido,
        direccion_entrega,
        costo_envio
    FROM dbo.Pedido
    ORDER BY fecha_pedido DESC;
END;
GO

-- =============================================
-- 5. CONSULTAR PEDIDOS POR DNI
-- =============================================

CREATE OR ALTER PROCEDURE dbo.sp_consultar_pedido_por_dni
    @dni_cliente VARCHAR(8)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        id_pedido,
        nombre_cliente,
        dni_cliente,
        producto,
        cantidad,
        precio_unitario,
        total,
        fecha_pedido,
        estado,
        tipo_pedido,
        direccion_entrega,
        costo_envio
    FROM dbo.Pedido
    WHERE dni_cliente = @dni_cliente
    ORDER BY fecha_pedido DESC;
END;
GO

-- =============================================
-- 6. ACTUALIZAR ESTADO DEL PEDIDO
-- =============================================

CREATE OR ALTER PROCEDURE dbo.sp_actualizar_estado
    @id_pedido INT,
    @nuevo_estado VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    IF @nuevo_estado NOT IN ('Pendiente', 'Enviado')
        THROW 50007, 'Estado de pedido no valido.', 1;

    UPDATE dbo.Pedido
    SET estado = @nuevo_estado
    WHERE id_pedido = @id_pedido;

    IF @@ROWCOUNT = 0
        THROW 50008, 'No se encontro el pedido.', 1;
END;
GO