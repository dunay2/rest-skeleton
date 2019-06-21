package person.service;

import couchdb.service.DbCouchService;
import couchdb.service.DbCouchServiceImpl;
import org.lightcouch.Document;
import person.dto.PersonDTO;
import service.DocumentService;


public class PersonServiceImpl implements DocumentService {

    public PersonServiceImpl() {
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void create(Document doc) {

        DbCouchService dbService = new DbCouchServiceImpl();

        dbService.create(doc);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getVersion() {

        Document person = new PersonDTO();
        return person.getId();

    }
}