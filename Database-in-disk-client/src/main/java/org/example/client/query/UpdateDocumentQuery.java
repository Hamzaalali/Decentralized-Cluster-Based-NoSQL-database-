package org.example.client.query;
import org.example.client.QueryManager;
import org.example.server_client.QueryType;
import org.example.server_client.ServerClientCommunicator;
import org.json.simple.JSONObject;

import java.net.Socket;
import java.util.Optional;
public class UpdateDocumentQuery extends DatabaseQuery{

    public UpdateDocumentQuery(QueryManager queryManager) {
        super(queryManager);
    }

    @Override
    public JSONObject execute(JSONObject query, Socket socket) {
        try{
            ServerClientCommunicator.sendJson(socket,query);
            JSONObject messageFromServer= ServerClientCommunicator.readJson(socket);
            return  queryManager.handleMessage(messageFromServer,query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
