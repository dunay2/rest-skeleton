package couchdb.util;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Document;
import org.lightcouch.Response;
import org.lightcouch.NoDocumentException;

import java.util.List;
import java.util.function.Supplier;

public class CouchConnector<E> {

//import org.jboss.resteasy.client.ClientRequest;
//http://www.lightcouch.org/lightcouch-guide.html


    private static CouchDbClient dbClient;
    private static CouchConnector instance;
    private Supplier<E> supplier;
    private static final String COUCH_CONFIG_FILE = "couchdb.properties";

    public static synchronized CouchConnector getInstance() {
        if (instance == null) {
            instance = new CouchConnector();
            dbClient = new CouchDbClient(COUCH_CONFIG_FILE);
        }
        return instance;
    }

    public JsonObject getDocumentById(String docId) {
            return dbClient.find(JsonObject.class, docId);
    }

    public String getVersion() {
        return dbClient.context().serverVersion();
    }

    public List<String> getAllDbs() {
        return dbClient.context().getAllDbs();
    }

    public Response updateDocument(Document doc) {
        return dbClient.update(doc);
    }

    public Response createDocument(Document doc) {
        boolean found = false;
        Response response=null;
        byte retries = 0;

        while (retries < 3 && !found) {
            String id = RandomUtil.unique();
            found = dbClient.contains(id);
            if (!found) {
                doc.setId(id);
                response=dbClient.save(doc);
                dbClient.save(doc);
                //https://www.programcreek.com/java-api-examples/?api=org.lightcouch.Response
                //eliminar conflicto
//    private Response saveJsonElement(JsonElement json) {
//        Response save;
//        if (json instanceof JsonObject) {
//            JsonObject obj = (JsonObject) json;
//            if (obj.get("_rev") == null) { //comprobar revision para ver si es nuevo?
//                save = couchClient.save(json);
//            } else {
//                save = couchClient.update(json);
//            }
//        } else {
//            save = couchClient.save(json);
//        }
//        return save;
//    }
                found = true;
            }
            retries++;
        }
        return response;
    }

    public Object find(Class<E> supplier, String id) {
        return dbClient.find(supplier, id);
    }

    //  public String deleteDocument(E entity, String path, String revision) {

//        Response resp = dbClient.remove(entity.get_id(), revision);

    //dbClient.

    //  resp = dbClient.purge(path, entity.get_id(), new String[]{revision});
    // System.out.println("remove object revision: " + entity.get_id() + ", repsonse: " + resp);
    //return true;
    //}
    //    return "not implmemented";
    //}

//    public String getDocument() {
//        return "not implmemented";
//    }
//
//    public <T> T findById(CouchTransaction transaction, String id) throws Exception {
//        logger.trace("METHOD EmlDao.findById -- document id :" + id);
//        final T obj = (T) GenericCouchDBDao.getDocument(transaction, databaseName, id, myClass);
//        if (obj == null) {
//            throw new CouchDBDocumentNotFound("Document not found in couch with id " + id);
//        }
//        return obj;
//
//
//        dbClient.
//    }


    // String baseURI = dbClient.getBaseUri().toString();
    // String dbURI = dbClient.getDBUri().toString();

    //String uri = baseURI + "_stats";
    //JsonObject stats = dbClient.findAny(JsonObject.class, uri);

/////////////// Get and populate a document

    // Foo foo = dbClient.find(Foo.class, "doc-id");
    // foo = dbClient.find(Foo.class, "doc-id", "doc-rev");

    //foo = dbClient.find(Foo.class, "doc-id", new Params().revsInfo().localSeq());
    /////////////////


//    static <T> T get(final String url, final String user, final String password, final String path, final Class<T> type, final boolean logErrorMessages) throws Exception {
//
//
//        prepareRequest
//
//        ClientRequest readRequest = prepareRequest(url, user, password)
//        T result = null;
//        ClientResponse<T> readResponse = null;
//        try {
//            readResponse = readRequest.get(type);
//            switch (readResponse.getStatus()) {
//                case 200:
//                    result = readResponse.getEntity();
//                    break;
//                case 400:
//                    if (logErrorMessages) {
//                        logger.error("Bad request to CouchDB: " + url + path);
//                    }
//                    throw new CouchDBBadRequestException();
//                case 401:
//                    if (logErrorMessages) {
//                        logger.error("The credentials sent are not valid, url: " + url + path);
//                    }
//                    throw new CouchDBUnauthorizedException();
//                case 404:
//                    //This is completely normal, some times the application is simply checking if a specific document exists
//                    break;
//                default:
//                    if (logErrorMessages) {
//                        logger.warn("Error performing get request on CouchDB. Status: " + readResponse.getStatus() + ". Url: " + url + path);
//                    }
//                    throw new InstanceNotAvailableException();
//            }
//        } catch (ConnectException connectException) {
//            throw new InstanceNotAvailableException();
//        } catch (Exception e) {
//            if (logErrorMessages) {
//                logger.error("Get: Unknown exception thrown contacting CouchDB", e);
//            }
//            throw e;
//        } finally {
//            if (readResponse != null) {
//                readResponse.releaseConnection();
//            }
//            try {
//                readRequest.getExecutor().close();
//            } catch (Exception e) {
//                //Error trying to close the connection
//            }
//        }
//
//        return result;
//    }
//
//    public ClientRequest prepareRequest(final String url, final String user, final String password) {
//        String credentials = user + ":" + password;
//        return prepareRequest(url, credentials);
//
//      String a=  dbClient.getBaseUri();
//    }
//
////    public  Integer getDatabaseNumberOfDocuments(final String databaseName, final String extraParameter) {
////        Integer result = null;
////        String path = "/" + databaseName + "/_all_docs" + extraParameter;
//
////        HashMap<String, Object> responseHashmap = getSilently(path, HashMap.class);
////        if ((responseHashmap != null) && responseHashmap.containsKey("rows")) {
////            ArrayList rows = (ArrayList) responseHashmap.get("rows");
////            result = rows.size();
////        }
////
////        return result;
////    }
}
