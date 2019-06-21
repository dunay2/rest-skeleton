package couchdb.service;

import com.google.gson.JsonObject;
import couchdb.util.CouchConnector;
import org.lightcouch.Document;


public class DbCouchServiceImpl implements DbCouchService {

    private CouchConnector couchConnector;

    public DbCouchServiceImpl() {

        couchConnector = CouchConnector.getInstance();
    }

    @Override
    public String getVersion() {

        return couchConnector.getVersion();
    }

    @Override
    public String createDocument(Document doc) {
        couchConnector.createDocument(doc);
        return "document created";
    }

    @Override
    public String updateDocument() {

        couchConnector.updateDocument();

        return "document created/update";
    }

    @Override
    public String deleteDocument() {
        return null;
    }

    @Override
    public JsonObject getDocumentById(String docId) {

        return couchConnector.getDocumentById(docId);
    }


    //  static <T> T getSilently(final String url, final String couchToken, final String path, final Class<T> type, final boolean logErrorMessages) {
//        try {
//            return get(url, couchToken, path, type, logErrorMessages);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    static <T> T getSilently(final String url, final String couchToken, final String path, final Class<T> type) {
//        try {
//            return get(url, couchToken, path, type);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    static <T> T getSilently(final CouchTransaction transaction, final String path, final Class<T> type) {
//        try {
//            return get(transaction, path, type);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    //    static <T> T getSilently(final String path, final Class<T> type, final boolean logErrorMessages) {
//        try {
//            return get(path, type, logErrorMessages);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    static <T> T getSilently(final String path, final Class<T> type) {
//        try {
//            return get(path, type);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    static <T> T get(final CouchTransaction transaction, final String path, final Class<T> type) throws Exception {
//        transaction.checkTransactionIsValid();
//        return get(path, type);
//    }

//    static <T> T get(final String path, final Class<T> type) throws Exception {
//        return get(path, type, true);
//    }
//
//    static <T> T get(final String path, final Class<T> type, final boolean logErrorMessages) throws Exception {
//        int i=0;
////        try {
////            try {
////                return get(selectedInstance.getUrl(), couchToken, path, type, logErrorMessages);
////            } catch (CouchDBUnauthorizedException e) {
////                //Retry with a new token, if it fails again, we will throw the error
////                refreshCouchToken();
////                return get(selectedInstance.getUrl(), couchToken, path, type, logErrorMessages);
////            }
////        } catch (InstanceNotAvailableException e) {
////            //Try to change the selected database only if the request was done for the selected database
////
////            throw e;
////        }
//        return  <T>type;
//    }

//    static <T> T get(final String url, final String couchToken, final String path, final Class<T> type) throws Exception {
//        return get(url, couchToken, path, type, true);
//    }

//    static <T> T get(final String url, final String couchToken, final String path, final Class<T> type, final boolean logErrorMessages) throws Exception {
//
//        ClientRequest readRequest = requestHelper.prepareCookieAuthRequest(url + path, couchToken);
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


    public static interface DbPingService {
    }
}
