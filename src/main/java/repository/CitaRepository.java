package repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cita;
import model.Consultorio;
import model.Doctor;
import model.Paciente;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorAndFechaHoraBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    List<Cita> findByConsultorioAndFechaHoraBetween(Consultorio consultorio, LocalDateTime start, LocalDateTime end);
    List<Cita> findByPacienteAndFechaHoraBetween(Paciente paciente, LocalDateTime start, LocalDateTime end);
}
