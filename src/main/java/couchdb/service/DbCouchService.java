package couchdb.service;

import com.google.gson.JsonObject;
import org.lightcouch.Document;

public interface DbCouchService {

     String getVersion();

     String createDocument(Document doc);

     String updateDocument();

     String deleteDocument();

     JsonObject getDocumentById(String docId);


}
