package couchdb.controller;
import couchdb.service.DbCouchServiceImpl;
import couchdb.service.DbCouchService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/web-resources")
public class Ping {

    @GET
    public String ping() {
        DbCouchService dbService = new DbCouchServiceImpl();
        return dbService.getVersion();
    }

}
