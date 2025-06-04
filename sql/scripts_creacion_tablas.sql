CREATE DATABASE EventosSostenibles;
USE EventosSostenibles;

CREATE TABLE Usuario (
    Id_Us INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Correo VARCHAR(50) NOT NULL UNIQUE,
    Contrasena VARCHAR(60) NOT NULL,
   
    CONSTRAINT VERIFICAR_CorreoUsuario CHECK (Correo REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')
);

CREATE TABLE Organizador (
    Id_O INT UNSIGNED AUTO_INCREMENT,
    Id_Us INT UNSIGNED NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Telefono CHAR(11) NOT NULL,
   
    CONSTRAINT pk_organizador PRIMARY KEY (Id_O, Id_Us),
    CONSTRAINT fk_organizador_usuario FOREIGN KEY (Id_Us) REFERENCES Usuario(Id_Us),
    CONSTRAINT VERIFICAR_Telefono CHECK (
    Telefono REGEXP '^[0-9]{3}-[0-9]{3}-[0-9]{3}$')
);

CREATE TABLE Participante (
    Id_P INT UNSIGNED AUTO_INCREMENT,
    Id_Us INT UNSIGNED NOT NULL,
    Num_Inscripciones INT UNSIGNED NOT NULL DEFAULT 0,
    
    CONSTRAINT PK_Participante PRIMARY KEY (Id_P, Id_Us),
    CONSTRAINT FK_Participante_Usuario FOREIGN KEY (Id_Us)
        REFERENCES Usuario(Id_Us)
);

CREATE TABLE Categoria (
    Id_C INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(30) NOT NULL,
    Descripcion TEXT NOT NULL
);

CREATE TABLE Evento (
    Id_E INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Fecha DATE NOT NULL,
    Hora_Inicio TIME NOT NULL,
    Duracion INT UNSIGNED NOT NULL,
    Estado ENUM('activo', 'cancelado', 'aplazado') DEFAULT 'activo' NOT NULL,
    Modalidad ENUM('online', 'presencial') NOT NULL,
    Ubicacion VARCHAR(100),
    Enlace VARCHAR(255),
    Id_C INT UNSIGNED NOT NULL,
    Id_O INT UNSIGNED NOT NULL,
    Id_Us INT UNSIGNED NOT NULL,

    CONSTRAINT fk_evento_categoria FOREIGN KEY (Id_C) REFERENCES Categoria(Id_C),
    CONSTRAINT fk_evento_organizador FOREIGN KEY (Id_O,Id_Us) REFERENCES Organizador(Id_O,Id_Us),

    CONSTRAINT chk_modalidad_ubicacion CHECK (
        (Modalidad = 'online' AND Ubicacion IS NULL) OR
        (Modalidad = 'presencial' AND Ubicacion IS NOT NULL)
    ),

    CONSTRAINT chk_modalidad_enlace CHECK (
        (Modalidad = 'online' AND Enlace IS NOT NULL) OR
        (Modalidad = 'presencial' AND Enlace IS NULL)
    ),

    CONSTRAINT chk_duracion_valida CHECK (Duracion > 0)
);



CREATE TABLE Inscribir (
    Id_P INT UNSIGNED NOT NULL,
    Id_Us INT UNSIGNED NOT NULL,
    Id_E INT UNSIGNED NOT NULL,
    Fecha DATE NOT NULL DEFAULT (CURDATE()),
    Estado ENUM('activo', 'inactivo') DEFAULT 'activo' NOT NULL,

    CONSTRAINT pk_inscribir PRIMARY KEY (Id_P,Id_Us,Id_E),  
    CONSTRAINT fk_inscribir_usuario FOREIGN KEY (Id_P,Id_Us) REFERENCES Participante(Id_P,Id_Us),
    CONSTRAINT fk_inscribir_evento FOREIGN KEY (Id_E) REFERENCES Evento(Id_E)
);


DELIMITER $$
DROP TRIGGER IF EXISTS trg_incrementar_inscripcion$$

CREATE TRIGGER trg_incrementar_inscripcion
AFTER INSERT ON Inscribir
FOR EACH ROW
BEGIN
    UPDATE Participante
    SET Num_Inscripciones = Num_Inscripciones + 1
    WHERE Id_P = NEW.Id_P AND Id_Us = NEW.Id_Us;
END$$

DELIMITER ;


DELIMITER $$
DROP TRIGGER IF EXISTS trg_solo_participante_u_organizador$$

CREATE TRIGGER trg_solo_participante_u_organizador BEFORE INSERT ON Participante
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM Organizador WHERE Id_Us = NEW.Id_Us) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Este usuario ya es organizador, no puede ser participante';
    END IF;
END$$

CREATE TRIGGER trg_no_organizador_si_participante BEFORE INSERT ON Organizador
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM Participante WHERE Id_Us = NEW.Id_Us) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Este usuario ya es participante, no puede ser organizador';
    END IF;
END$$

DELIMITER ;
