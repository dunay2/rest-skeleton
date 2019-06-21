package couchdb.service;

import org.lightcouch.Document;

public interface DbCouchService {

    public String getVersion();

    public String create(Document doc);

    public String update();

    public String delete();


}
