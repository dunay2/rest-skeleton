package couchdb.util;

import org.lightcouch.CouchDbClient;
import org.lightcouch.Document;
import org.lightcouch.Response;
import java.util.List;
import java.util.function.Supplier;

public class CouchConnector<E> {

    private static CouchDbClient dbClient;
    private static CouchConnector instance;
    private Supplier<E> supplier;

    public static synchronized CouchConnector getInstance(){
        if(instance == null){
            instance = new CouchConnector();
            dbClient = new CouchDbClient("couchdb.properties");
        }
        return instance;
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
            String id = RandomUtil.unique() ;
            found = dbClient.contains(id);
            if (!found) {
                doc.setId(id);
                response=dbClient.save(doc);
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


//    public  Integer getDatabaseNumberOfDocuments(final String databaseName, final String extraParameter) {
//        Integer result = null;
//        String path = "/" + databaseName + "/_all_docs" + extraParameter;

//        HashMap<String, Object> responseHashmap = getSilently(path, HashMap.class);
//        if ((responseHashmap != null) && responseHashmap.containsKey("rows")) {
//            ArrayList rows = (ArrayList) responseHashmap.get("rows");
//            result = rows.size();
//        }
//
//        return result;
//    }


}
