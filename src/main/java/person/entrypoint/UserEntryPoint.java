package person.entrypoint;

import org.apache.http.HttpStatus;
import org.lightcouch.NoDocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import person.dto.PersonDTO;
import person.requestModel.CreatePersonRequestModel;
import person.service.PersonServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/web-operations")
public class UserEntryPoint {

    @Autowired
    private PersonServiceImpl person;
    @Autowired
    private PersonDTO personDTO;

    @PUT
    @Path("/update")
    public Response update(CreatePersonRequestModel requestObject) {

        BeanUtils.copyProperties(requestObject, personDTO);
        person.update(personDTO);

        String result = "updated";
        return Response.status(HttpStatus.SC_OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( {MediaType.APPLICATION_JSON})
    @Path("/create")
    public Response create(CreatePersonRequestModel requestObject) {

        BeanUtils.copyProperties(requestObject, personDTO);

        //make this return an object
        person.create(personDTO);

        String result = "Person created";
        return Response.status(HttpStatus.SC_CREATED).entity(result).build();
    }

    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") String id) {

        String returnedValue = "";
        int responseStatus;

        try {
            returnedValue = person.getById(id).toString();
            responseStatus = HttpStatus.SC_OK;
        } catch (NoDocumentException e) {
            returnedValue = e.getMessage();
            responseStatus = HttpStatus.SC_NOT_FOUND;
        }

        return Response.status(responseStatus).entity(returnedValue).build();

    }
}