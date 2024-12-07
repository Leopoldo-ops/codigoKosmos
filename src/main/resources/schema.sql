CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido_paterno VARCHAR(50),
    apellido_materno VARCHAR(50),
    especialidad VARCHAR(50)
);

CREATE TABLE consultorio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT,
    piso INT
);

CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50)
);

CREATE TABLE cita (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT,
    consultorio_id BIGINT,
    paciente_id BIGINT,
    fecha_hora TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (consultorio_id) REFERENCES consultorio(id),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);