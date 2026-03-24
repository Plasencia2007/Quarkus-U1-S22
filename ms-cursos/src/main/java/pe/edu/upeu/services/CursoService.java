package pe.edu.upeu.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pe.edu.upeu.dtos.CursoRequest;
import pe.edu.upeu.dtos.CursoResponse;
import pe.edu.upeu.entities.Curso;
import pe.edu.upeu.errors.CursoNotFoundException;
import pe.edu.upeu.mappers.CursoMapper;
import pe.edu.upeu.repositories.CursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CursoService {

    @Inject
    CursoRepository repository;

    @Inject
    CursoMapper mapper;

    public List<CursoResponse> listAll() {
        return repository.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public CursoResponse findById(Long id) {
        Curso curso = repository.findByIdOptional(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso no encontrado con ID: " + id));
        return mapper.toResponse(curso);
    }

    @Transactional
    public CursoResponse create(CursoRequest request) {
        Curso curso = mapper.toEntity(request);
        repository.persist(curso);
        return mapper.toResponse(curso);
    }

    @Transactional
    public CursoResponse update(Long id, CursoRequest request) {
        Curso curso = repository.findByIdOptional(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso no encontrado con ID: " + id));
        
        curso.setNombre(request.getNombre());
        curso.setDescripcion(request.getDescripcion());
        curso.setCreditos(request.getCreditos());
        
        return mapper.toResponse(curso);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new CursoNotFoundException("Curso no encontrado con ID: " + id);
        }
    }
}
