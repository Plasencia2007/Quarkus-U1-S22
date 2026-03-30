package pe.edu.upeu.dtos;

import java.time.LocalDate;

public class MatriculaResponse {
    private Long id;
    private AlumnoDTO alumno;
    private CursoDTO curso;
    private String estado;
    private LocalDate fechaMatricula;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AlumnoDTO getAlumno() { return alumno; }
    public void setAlumno(AlumnoDTO alumno) { this.alumno = alumno; }

    public CursoDTO getCurso() { return curso; }
    public void setCurso(CursoDTO curso) { this.curso = curso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(LocalDate fechaMatricula) { this.fechaMatricula = fechaMatricula; }
}
