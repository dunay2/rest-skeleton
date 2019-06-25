package employee.entrypoint;

import org.apache.http.HttpStatus;
import org.lightcouch.NoDocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import employee.dto.EmployeeDTO;
import employee.requestModel.CreateEmployeeRequestModel;
import employee.requestModel.UpdateEmployeeRequestModel;
import employee.service.EmployeeServiceImpl;

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
public class EmployeeEntryPoint {

    @Autowired
    private EmployeeServiceImpl person;
    @Autowired
    private EmployeeDTO personDTO;

    @GET
    @Path("/get/{id}")
    public String update(@PathParam("id")String id ) {
        return person.find(id).toString();
    }

    //Implementar
    @PUT
    @Path("/update")
    public String update(UpdateEmployeeRequestModel requestObject) {
        //org.lightcouch.DocumentConflictException: Conflict gestionar las versiones
        BeanUtils.copyProperties(requestObject, personDTO);
       person.update(personDTO);
        String result = "updated";//devolver el objeto nuevo con la actualización
        return result;
     //   return Response.status(HttpStatus.SC_OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( {MediaType.APPLICATION_JSON})
    @Path("/create")
         //check invalid entries as documents with no commas
    public Response create(CreateEmployeeRequestModel requestObject) {

        BeanUtils.copyProperties(requestObject, personDTO);
        person.create(personDTO);

        String result = "Employee created";
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