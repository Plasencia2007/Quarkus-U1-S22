package pe.edu.upeu.dtos;

public class MatriculaRequest {
    private Long alumnoId;
    private Long cursoId;
    private String estado;

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
