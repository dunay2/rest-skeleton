package service;

import org.lightcouch.Document;
import org.lightcouch.Response;

public interface DocumentService {

    Response update(Document doc);
    void delete(String docId);
    public Response update(Document doc);
    void create(Document doc);
    Document find(String id);

}
