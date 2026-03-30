package pe.edu.upeu.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.upeu.entities.Matricula;

@ApplicationScoped
public class MatriculaRepository implements PanacheRepository<Matricula> {
}
