package org.example.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NoSQlClient {
    private Socket socket;
    private QueryManager queryManager;
    public void connect(String hostUrl,int port){
        try{
            socket=new Socket(hostUrl,port);
            queryManager=new QueryManager(socket,hostUrl);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public QueryManager getQueryManager(){
        return queryManager;
    }


}
