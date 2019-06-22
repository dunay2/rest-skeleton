package person.service;

import couchdb.service.DbCouchService;
import couchdb.service.DbCouchServiceImpl;
import org.lightcouch.Document;
import org.lightcouch.Response;
import person.dto.PersonDTO;
import service.DocumentService;

public class PersonServiceImpl implements DocumentService {
    private DbCouchService dbService = new DbCouchServiceImpl();

    public PersonServiceImpl() {
    }

    @Override
    public void delete() {
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