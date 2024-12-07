package service;


import model.*;
import repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

	@Autowired
    private CitaRepository citaRepository;

    public boolean validarCita(Cita cita) {
        LocalDateTime fechaHora = cita.getFechaHora();
        LocalDateTime inicioDia = fechaHora.toLocalDate().atStartOfDay();
        LocalDateTime finDia = fechaHora.toLocalDate().atTime(23, 59);

        // Validar doctor con 8 citas máximo
        if (citaRepository.findByDoctorAndFechaHoraBetween(cita.getDoctor(), inicioDia, finDia).size() >= 8) {
            return false;
        }

        // Validar consultorio no ocupado
        if (!citaRepository.findByConsultorioAndFechaHoraBetween(cita.getConsultorio(), fechaHora.minusHours(1), fechaHora.plusHours(1)).isEmpty()) {
            return false;
        }

        // Validar paciente sin citas cercanas
        if (!citaRepository.findByPacienteAndFechaHoraBetween(cita.getPaciente(), fechaHora.minusHours(2), fechaHora.plusHours(2)).isEmpty()) {
            return false;
        }

        return true;
    }

    public Cita guardarCita(Cita cita) {
        if (validarCita(cita)) {
            return citaRepository.save(cita);
        }
        throw new IllegalArgumentException("Cita no válida");
    }

    public List<Cita> consultarCitasPorDoctor(Long doctorId, LocalDateTime fecha) {
        return citaRepository.findByDoctorAndFechaHoraBetween(new Doctor(doctorId), fecha.toLocalDate().atStartOfDay(), fecha.toLocalDate().atTime(23, 59));
    }

    // Otras funciones...
}
