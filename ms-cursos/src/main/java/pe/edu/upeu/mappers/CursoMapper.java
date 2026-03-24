package pe.edu.upeu.mappers;

import pe.edu.upeu.dtos.CursoRequest;
import pe.edu.upeu.dtos.CursoResponse;
import pe.edu.upeu.entities.Curso;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoMapper {
    
    public Curso toEntity(CursoRequest request) {
        if (request == null) return null;
        Curso curso = new Curso();
        curso.setNombre(request.getNombre());
        curso.setDescripcion(request.getDescripcion());
        curso.setCreditos(request.getCreditos());
        return curso;
    }

    public CursoResponse toResponse(Curso curso) {
        if (curso == null) return null;
        CursoResponse response = new CursoResponse();
        response.setId(curso.getId());
        response.setNombre(curso.getNombre());
        response.setDescripcion(curso.getDescripcion());
        response.setCreditos(curso.getCreditos());
        return response;
    }
}
