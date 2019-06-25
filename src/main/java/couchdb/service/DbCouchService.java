package couchdb.service;

import com.google.gson.JsonObject;
import org.lightcouch.Document;
import org.lightcouch.Response;

public interface DbCouchService<E>{
     String getVersion();
     String create(Document doc);
     Response update(Document doc);
     String delete();
     Object find(Class<E> supplier, String id);
}
