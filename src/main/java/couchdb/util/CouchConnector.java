package couchdb.util;


import org.lightcouch.CouchDbClient;
import org.lightcouch.Document;

import java.util.List;




public class CouchConnector {

    private static CouchDbClient dbClient;
    private static CouchConnector instance;


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

    public String updateDocument() {
        return "Document updated";

    }

    public String createDocument(Document doc) {

        boolean found = false;
        byte retries = 0;

        while (retries < 3 && !found) {
            String id = RandomUtil.unique() ;
            found = dbClient.contains(id);
            if (!found) {
                doc.setId(id);
                dbClient.save(doc);
                found = true;
            }
            retries++;
        }

        //Response response = dbClient.save(foo);

        //dbClient.update(foo);

        //Map<String, Object> map = new HashMap<>();
        //map.put("_id", "doc-id");
        //map.put("title", "value");
        //dbClient.save(map);

        // dbClient.shutdown();

        //JsonObject json = new JsonObject();
        //json.addProperty("_id", "doc-id");
        //json.add("array", new JsonArray());
        //dbClient.save(json);

        //String jsonstr = "{\"title\":\"val\"}";
        //JsonObject jsonobj = dbClient.getGson().fromJson(jsonstr, JsonObject.class);
        //dbClient.save(jsonobj);
        return "saved";
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

    public String getDocument() {
        return "not implmemented";
    }


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
