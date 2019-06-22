package person.entrypoint;

import org.lightcouch.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import person.dto.PersonDTO;
import person.requestModel.CreatePersonRequestModel;
import person.requestModel.UpdatePersonRequestModel;
import person.service.PersonServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/web-operations")
public class UserEntryPoint {

    @Autowired
    private PersonServiceImpl person;
    @Autowired
    private PersonDTO personDTO;

    @PUT
    @Path("/update")
    public String update(UpdatePersonRequestModel requestObject) {
        //org.lightcouch.DocumentConflictException: Conflict gestionar las versiones
        BeanUtils.copyProperties(requestObject, personDTO);
        return person.update(personDTO).toString();
        //devolver respuesta json gson?
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/create")
    public String create(CreatePersonRequestModel requestObject) {
        //check invalid entries as documents with no commas
        BeanUtils.copyProperties(requestObject, personDTO);
        person.create(personDTO);

        return "created";
    }
}