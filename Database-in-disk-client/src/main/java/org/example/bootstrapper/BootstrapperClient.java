package org.example.bootstrapper;
import org.example.client.QueryManager;
import org.example.server_client.ServerClientCommunicator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
public class BootstrapperClient {


    private Socket socket;
    public  BootstrapperClient(String hostUrl,int port){
        try{
            socket=new Socket(hostUrl,port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Long register(String username,String password){//returns the tcp port that the user can connect to
        try{
            JSONObject request=new JSONObject();
            request.put("username",username);
            request.put("password",password);
            request.put("requestType",BootstrapperRequests.REGISTER.toString());
            ServerClientCommunicator.sendJson(socket,request);
            JSONObject messageFromServer=ServerClientCommunicator.readJson(socket);
            if(((Long)messageFromServer.get("code_number"))==1){
                throw new RuntimeException((String) messageFromServer.get("error_message"));
            }
            return (Long)messageFromServer.get("tcpPort");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
