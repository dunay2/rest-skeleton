package person.service;

import com.google.gson.JsonObject;
import couchdb.service.DbCouchService;
import couchdb.service.DbCouchServiceImpl;
import org.lightcouch.Document;
//import person.dto.PersonDTO;
import service.DocumentService;

public class PersonServiceImpl implements DocumentService {

    private DbCouchService dbService = new DbCouchServiceImpl();

    public PersonServiceImpl() {
    }

    @Override
    public void delete(String docId) {
    }

    @Override
    public void update(Document doc) {
    //create revision?
    }

    @Override
    public void create(Document doc) {
        dbService.createDocument(doc);
    }

    @Override
    public JsonObject getById(String docId) {
        return dbService.getDocumentById(docId);
    }
}