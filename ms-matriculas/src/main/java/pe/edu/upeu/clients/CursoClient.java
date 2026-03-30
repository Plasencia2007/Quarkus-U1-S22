package pe.edu.upeu.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.edu.upeu.dtos.CursoDTO;

@Path("/cursos")
@RegisterRestClient(configKey = "pe.edu.upeu.clients.CursoClient")
public interface CursoClient {

    @GET
    @Path("/{id}")
    CursoDTO findById(@PathParam("id") Long id);
}
