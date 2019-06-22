package service;

import org.lightcouch.Document;
import org.lightcouch.Response;

public interface DocumentService {

    void delete();
    Response update(Document doc);
    void create(Document doc);
    Document find(String id);

}
