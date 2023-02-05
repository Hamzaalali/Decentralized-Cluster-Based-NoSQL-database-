package org.example.client.query.factory;

import org.example.client.QueryManager;
import org.example.client.query.*;
import org.example.server_client.QueryType;

import java.util.HashMap;
import java.util.Map;
public class DatabaseQueryFactory {
    public Map<QueryType, DatabaseQuery> databaseQueryMap(QueryManager queryManager){
        Map<QueryType,DatabaseQuery> databaseQueryMap=new HashMap<>();
        databaseQueryMap.put(QueryType.CREATE_DATABASE,new CreateDatabaseQuery(queryManager));
        databaseQueryMap.put(QueryType.CREATE_COLLECTION,new CreateCollectionQuery(queryManager));
        databaseQueryMap.put(QueryType.CREATE_DOCUMENT,new CreateDocumentQuery(queryManager));
        databaseQueryMap.put(QueryType.CREATE_INDEX,new CreateIndexQuery(queryManager));
        databaseQueryMap.put(QueryType.FIND,new FindQuery(queryManager));
        databaseQueryMap.put(QueryType.FIND_ALL,new FindAllQuery(queryManager));
        databaseQueryMap.put(QueryType.DELETE_DATABASE,new DeleteDatabaseQuery(queryManager));
        databaseQueryMap.put(QueryType.DELETE_COLLECTION,new DeleteCollectionQuery(queryManager));
        databaseQueryMap.put(QueryType.DELETE_DOCUMENT,new DeleteDocumentQuery(queryManager));
        databaseQueryMap.put(QueryType.UPDATE_DOCUMENT,new UpdateDocumentQuery(queryManager));
        databaseQueryMap.put(QueryType.PING,new PingQuery(queryManager));
        return databaseQueryMap;
    }
}
