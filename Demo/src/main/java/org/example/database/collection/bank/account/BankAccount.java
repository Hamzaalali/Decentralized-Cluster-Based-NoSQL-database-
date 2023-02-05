package org.example.database.collection.bank.account;

import org.example.database.NoSqlDocument;
import org.example.json.JsonBuilder;
import org.json.simple.JSONObject;

public class BankAccount implements NoSqlDocument {
    private String clientName;
    private double balance;
    private boolean hasInsurance;
    private BankAccountTypes accountType;
    private long accountNumber;

    public BankAccount(){
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public BankAccountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(BankAccountTypes accountType) {
        this.accountType = accountType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static BankAccount of(JSONObject accountObject){
        BankAccount bankAccount=new BankAccount();
        bankAccount.setAccountType(BankAccountTypes.valueOf((String) accountObject.get("accountType")));
        bankAccount.setClientName((String) accountObject.get("clientName"));
        bankAccount.setBalance((double)accountObject.get("balance"));
        bankAccount.setHasInsurance((boolean) accountObject.get("hasInsurance"));
        bankAccount.setAccountNumber((long) accountObject.get("accountNumber"));
        return bankAccount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject accountJson= JsonBuilder.getBuilder()
                .add("accountNumber",accountNumber)
                .add("clientName", clientName)
                .add("balance",balance)
                .add("hasInsurance",hasInsurance)
                .add("accountType",accountType.toString())
                .build();
        return accountJson;
    }

    @Override
    public String toString() {
        return "clientName : " + clientName+"\n"+
                "balance : " + balance +"\n"+
                "hasInsurance : " + hasInsurance +"\n"+
                "accountType : " + accountType +"\n"+
                "accountNumber : " + accountNumber+"\n";
    }
}
