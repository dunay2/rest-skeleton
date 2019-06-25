package employee.service;

import couchdb.service.DbCouchService;
import couchdb.service.DbCouchServiceImpl;
import org.lightcouch.Document;
import org.lightcouch.Response;
import employee.dto.PersonDTO;
import service.DocumentService;

public class EmployeeServiceImpl implements DocumentService {
    private DbCouchService dbService = new DbCouchServiceImpl();


    public EmployeeServiceImpl() {
    }

    @Override
    public void delete(String docId) {
    }

    @Override
    public Response update(Document doc) {
        return dbService.update(doc);
    }

    @Override
    public void create(Document doc) {
        dbService.create(doc);
    }

    @Override
    public Document find(String id) {
        return (Document) dbService.find(PersonDTO.class,id);
    }
}