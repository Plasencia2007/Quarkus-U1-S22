package pe.edu.upeu.mappers;

import pe.edu.upeu.dtos.AlumnoRequest;
import pe.edu.upeu.dtos.AlumnoResponse;
import pe.edu.upeu.entities.Alumno;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlumnoMapper {
    
    public Alumno toEntity(AlumnoRequest request) {
        if (request == null) return null;
        Alumno alumno = new Alumno();
        alumno.setNombres(request.getNombres());
        alumno.setApellidos(request.getApellidos());
        alumno.setCorreo(request.getCorreo());
        return alumno;
    }

    public AlumnoResponse toResponse(Alumno alumno) {
        if (alumno == null) return null;
        AlumnoResponse response = new AlumnoResponse();
        response.setId(alumno.getId());
        response.setNombres(alumno.getNombres());
        response.setApellidos(alumno.getApellidos());
        response.setCorreo(alumno.getCorreo());
        return response;
    }
}
