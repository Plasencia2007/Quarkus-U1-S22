package pe.edu.upeu.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.edu.upeu.clients.AlumnoClient;
import pe.edu.upeu.clients.CursoClient;
import pe.edu.upeu.dtos.AlumnoDTO;
import pe.edu.upeu.dtos.CursoDTO;
import pe.edu.upeu.dtos.MatriculaRequest;
import pe.edu.upeu.dtos.MatriculaResponse;
import pe.edu.upeu.entities.Matricula;
import pe.edu.upeu.errors.MatriculaNotFoundException;
import pe.edu.upeu.mappers.MatriculaMapper;
import pe.edu.upeu.repositories.MatriculaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MatriculaService {

    @Inject
    MatriculaRepository repository;

    @Inject
    MatriculaMapper mapper;

    @RestClient
    @Inject
    AlumnoClient alumnoClient;

    @RestClient
    @Inject
    CursoClient cursoClient;

    public List<MatriculaResponse> listAll() {
        return repository.listAll().stream()
                .map(this::enrichMatriculaResponse)
                .collect(Collectors.toList());
    }

    public MatriculaResponse findById(Long id) {
        Matricula matricula = repository.findByIdOptional(id)
                .orElseThrow(() -> new MatriculaNotFoundException("Matricula no encontrada con ID: " + id));
        return enrichMatriculaResponse(matricula);
    }

    @Transactional
    public MatriculaResponse create(MatriculaRequest request) {
        // Validar que existan
        AlumnoDTO alumno = alumnoClient.findById(request.getAlumnoId());
        CursoDTO curso = cursoClient.findById(request.getCursoId());

        Matricula matricula = mapper.toEntity(request);
        matricula.setFechaMatricula(LocalDate.now());
        repository.persist(matricula);

        MatriculaResponse response = mapper.toResponse(matricula);
        response.setAlumno(alumno);
        response.setCurso(curso);
        return response;
    }

    @Transactional
    public MatriculaResponse update(Long id, MatriculaRequest request) {
        Matricula matricula = repository.findByIdOptional(id)
                .orElseThrow(() -> new MatriculaNotFoundException("Matricula no encontrada con ID: " + id));
        
        AlumnoDTO alumno = alumnoClient.findById(request.getAlumnoId());
        CursoDTO curso = cursoClient.findById(request.getCursoId());

        matricula.setAlumnoId(request.getAlumnoId());
        matricula.setCursoId(request.getCursoId());
        matricula.setEstado(request.getEstado());
        
        MatriculaResponse response = mapper.toResponse(matricula);
        response.setAlumno(alumno);
        response.setCurso(curso);
        return response;
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new MatriculaNotFoundException("Matricula no encontrada con ID: " + id);
        }
    }

    private MatriculaResponse enrichMatriculaResponse(Matricula matricula) {
        MatriculaResponse response = mapper.toResponse(matricula);
        try {
             AlumnoDTO alumno = alumnoClient.findById(matricula.getAlumnoId());
             response.setAlumno(alumno);
        } catch (Exception e) {
             // Silencioso si falla
             System.out.println("Error obteniendo Alumno: " + e.getMessage());
        }
        try {
             CursoDTO curso = cursoClient.findById(matricula.getCursoId());
             response.setCurso(curso);
        } catch (Exception e) {
             // Silencioso si falla
             System.out.println("Error obteniendo Curso: " + e.getMessage());
        }
        return response;
    }
}
