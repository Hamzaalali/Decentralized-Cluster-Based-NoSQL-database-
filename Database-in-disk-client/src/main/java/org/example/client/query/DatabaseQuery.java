package org.example.client.query;


import org.example.client.QueryManager;
import org.json.simple.JSONObject;

import java.net.Socket;

public abstract class DatabaseQuery {

    protected QueryManager queryManager;
    public DatabaseQuery(QueryManager queryManager){
        this.queryManager=queryManager;
    }
    public abstract JSONObject execute(JSONObject query, Socket socket);

}
