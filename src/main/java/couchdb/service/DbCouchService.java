package couchdb.service;

import org.lightcouch.Document;
import org.lightcouch.Response;

public interface DbCouchService<E>{
    public String getVersion();
    public String create(Document doc);
    public Response update(Document doc);
    public String delete();
    public Object find(Class<E> supplier, String id);
}
