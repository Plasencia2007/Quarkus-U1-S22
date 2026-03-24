package pe.edu.upeu.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.upeu.entities.Curso;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<Curso> {
}
