package service;

import org.lightcouch.Document;

public interface DocumentService {

    void delete();
    void update();
    void create(Document doc);
    String getId();
    String getVersion();

}
