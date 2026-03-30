package pe.edu.upeu.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.edu.upeu.dtos.AlumnoDTO;

@Path("/alumnos")
@RegisterRestClient(configKey = "pe.edu.upeu.clients.AlumnoClient")
public interface AlumnoClient {

    @GET
    @Path("/{id}")
    AlumnoDTO findById(@PathParam("id") Long id);
}
