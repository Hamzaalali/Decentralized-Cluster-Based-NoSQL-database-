package org.example.database.collection.bank.account;
import org.example.client.QueryManager;
import org.example.database.BankDatabase;
import org.example.json.JsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoSqlBankAccountDao implements BankAccountDao {
    private static volatile NoSqlBankAccountDao instance;

    private QueryManager queryManager= BankDatabase.getInstance().getQueryManager();
    private String databaseName=BankDatabase.getInstance().getDatabaseName();
    private String collectionName="accounts";
    private NoSqlBankAccountDao(){
        JSONObject indexProperty=JsonBuilder.getBuilder()
                .add("accountNumber","")
                .build();
        queryManager.createIndex(databaseName,collectionName,indexProperty);
    }

    private String getId(long accountNumber) {
        JSONObject searchJson= JsonBuilder.getBuilder()
                .add("accountNumber",accountNumber)
                .build();
        JSONArray accountArray=queryManager.find(databaseName,collectionName,searchJson);
        if(accountArray.size()==0){
            return null;
        }
        JSONObject accountJson= (JSONObject) accountArray.get(0);
        return (String) accountJson.get("id");
    }
    @Override
    public void addBankAccount(BankAccount account) {
        BankAccount bankAccount=getBankAccount(account.getAccountNumber());
        if(bankAccount!=null){
            System.out.println("Account Already Exists!");
            return;
        }
        queryManager.createDocument(databaseName,collectionName,account.toJson());
    }

    @Override
    public void deleteBankAccount(long accountNumber) {
        String id=getId(accountNumber);
        if(id==null){
            System.out.println("No Account Found With This Number");
            return;
        }
        queryManager.deleteDocument(databaseName,collectionName,id);
    }

    @Override
    public void updateBankAccountBalance(long accountNumber,double balance) {
        JSONObject updateJson=JsonBuilder.getBuilder()
                        .add("balance",balance)
                                .build();
        String id=getId(accountNumber);
        if(id==null){
            System.out.println("No Account Found With This Number");
            return;
        }
        queryManager.updateDocument(databaseName,collectionName,id,updateJson);
    }

    @Override
    public BankAccount getBankAccount(long accountNumber) {
        JSONObject searchJson= JsonBuilder.getBuilder()
                        .add("accountNumber",accountNumber)
                                .build();
        JSONArray accounts=queryManager.find(databaseName,collectionName,searchJson);
        if(accounts.size()==0){
            return null;
        }
        JSONObject accountJson=(JSONObject)accounts.get(0);
        return BankAccount.of(accountJson);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        JSONArray accounts=queryManager.findAll(databaseName,collectionName);
        List<BankAccount>accountList=new ArrayList<>();
        for(Object accountObject:accounts.toArray()){
            accountList.add(BankAccount.of((JSONObject) accountObject));
        }
        return accountList;
    }
    public static NoSqlBankAccountDao getInstance() {
        NoSqlBankAccountDao result = instance;
        if (result != null) {
            return result;
        }
        synchronized(NoSqlBankAccountDao.class) {
            if (instance == null) {
                instance = new NoSqlBankAccountDao();
            }
            return instance;
        }
    }
}
