package service;

import com.google.gson.JsonObject;
import org.lightcouch.Document;

public interface DocumentService {

    void delete(String docId);

    void update(Document doc);

    void create(Document doc);

    JsonObject getById(String docId);
}
