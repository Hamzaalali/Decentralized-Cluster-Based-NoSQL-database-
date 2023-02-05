package org.example.client.query;
import jdk.net.Sockets;
import org.example.client.QueryManager;
import org.example.server_client.QueryType;
import org.example.server_client.ServerClientCommunicator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FindQuery extends DatabaseQuery {

    public FindQuery(QueryManager queryManager) {
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
