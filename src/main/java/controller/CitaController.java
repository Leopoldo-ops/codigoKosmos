package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

	@Autowired
    private CitaService citaService;
	

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.guardarCita(cita);
    }
    
    @GetMapping("/crear")
    public String mostrarFormularioCrearCita(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas/crear";
    }

    @PostMapping("/crear")
    public String crearCita(@ModelAttribute Cita cita, Model model) {
        try {
            citaService.guardarCita(cita);
            return "redirect:/citas/listar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "citas/crear";
        }
    }

    

    @GetMapping("/doctor/{doctorId}")
    public List<Cita> obtenerCitasPorDoctor(@PathVariable Long doctorId, @RequestParam String fecha) {
        return citaService.consultarCitasPorDoctor(doctorId, LocalDate.parse(fecha).atStartOfDay());
    }
}
