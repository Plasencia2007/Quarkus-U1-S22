package pe.edu.upeu.errors;

public class MatriculaNotFoundException extends RuntimeException {
    public MatriculaNotFoundException(String message) {
        super(message);
    }
}
