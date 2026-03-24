package pe.edu.upeu.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pe.edu.upeu.dtos.AlumnoRequest;
import pe.edu.upeu.dtos.AlumnoResponse;
import pe.edu.upeu.entities.Alumno;
import pe.edu.upeu.errors.AlumnoNotFoundException;
import pe.edu.upeu.mappers.AlumnoMapper;
import pe.edu.upeu.repositories.AlumnoRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlumnoService {

    @Inject
    AlumnoRepository repository;

    @Inject
    AlumnoMapper mapper;

    public List<AlumnoResponse> listAll() {
        return repository.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public AlumnoResponse findById(Long id) {
        Alumno alumno = repository.findByIdOptional(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno no encontrado con ID: " + id));
        return mapper.toResponse(alumno);
    }

    @Transactional
    public AlumnoResponse create(AlumnoRequest request) {
        Alumno alumno = mapper.toEntity(request);
        repository.persist(alumno);
        return mapper.toResponse(alumno);
    }

    @Transactional
    public AlumnoResponse update(Long id, AlumnoRequest request) {
        Alumno alumno = repository.findByIdOptional(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno no encontrado con ID: " + id));
        
        alumno.setNombres(request.getNombres());
        alumno.setApellidos(request.getApellidos());
        alumno.setCorreo(request.getCorreo());
        
        return mapper.toResponse(alumno);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new AlumnoNotFoundException("Alumno no encontrado con ID: " + id);
        }
    }
}
