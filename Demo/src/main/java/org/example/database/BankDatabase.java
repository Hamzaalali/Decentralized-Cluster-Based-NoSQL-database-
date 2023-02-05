package org.example.database;
import org.example.client.NoSQlClient;
import org.example.client.QueryManager;
import org.example.database.collection.CollectionsFactory;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Objects;

public class BankDatabase {
    private static BankDatabase instance;
    private List<NoSqlCollection>collections;
    private String databaseName="bank";
    private QueryManager queryManager;

    public void init(){
        collections= CollectionsFactory.getInstance().getCollections();;
        NoSQlClient noSQlClient=new NoSQlClient();
        noSQlClient.connect(DatabaseConfig.HOSTNAME,DatabaseConfig.PORT);
        queryManager=noSQlClient.getQueryManager();
        queryManager.login(DatabaseConfig.USERNAME,DatabaseConfig.PASSWORD);
        createDatabase();
        createCollections();
    }
    private void createDatabase(){
        try{
            queryManager.createDatabase(databaseName);
        }catch (Exception e){
            //IGNORE
        }
    }
    private void createCollections(){
        for(NoSqlCollection collection:collections){
            try{
                queryManager.createCollection(databaseName,collection.getCollectionName(),collection.getSchema());
            }catch (Exception e){
                //IGNORE
            }
        }
    }
    public QueryManager getQueryManager() {
        return queryManager;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static BankDatabase getInstance(){
        if(instance==null){
            instance=new BankDatabase();
            return instance;
        }
        return instance;
    }
}
