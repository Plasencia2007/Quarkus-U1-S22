package pe.edu.upeu.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.upeu.entities.Alumno;

@ApplicationScoped
public class AlumnoRepository implements PanacheRepository<Alumno> {
}
