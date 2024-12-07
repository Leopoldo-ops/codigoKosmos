package repository;

import model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}