package org.example.database.collection;

import org.example.database.NoSqlCollection;
import org.example.database.collection.bank.account.BankAccountCollection;

import java.util.ArrayList;
import java.util.List;

public class CollectionsFactory {
    private static CollectionsFactory instance;
    private CollectionsFactory(){

    }
    public List<NoSqlCollection> getCollections(){
        List<NoSqlCollection>collections=new ArrayList<>();
        collections.add(new BankAccountCollection());
        return collections;
    }
    public static CollectionsFactory getInstance(){
        if(instance==null){
            return new CollectionsFactory();
        }
        return instance;
    }
}
