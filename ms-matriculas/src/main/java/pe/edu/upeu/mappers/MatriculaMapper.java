package pe.edu.upeu.mappers;

import pe.edu.upeu.dtos.MatriculaRequest;
import pe.edu.upeu.dtos.MatriculaResponse;
import pe.edu.upeu.entities.Matricula;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatriculaMapper {
    
    public Matricula toEntity(MatriculaRequest request) {
        if (request == null) return null;
        Matricula matricula = new Matricula();
        matricula.setAlumnoId(request.getAlumnoId());
        matricula.setCursoId(request.getCursoId());
        matricula.setEstado(request.getEstado());
        return matricula;
    }

    // toResponse takes the populated DTO objects from the clients
    public MatriculaResponse toResponse(Matricula matricula) {
        if (matricula == null) return null;
        MatriculaResponse response = new MatriculaResponse();
        response.setId(matricula.getId());
        response.setEstado(matricula.getEstado());
        response.setFechaMatricula(matricula.getFechaMatricula());
        return response;
    }
}
