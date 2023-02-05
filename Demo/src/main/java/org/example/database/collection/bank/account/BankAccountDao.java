package org.example.database.collection.bank.account;

import org.example.json.JsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface BankAccountDao {
    public void addBankAccount(BankAccount account);
    public void deleteBankAccount(long accountNumber);
    public void updateBankAccountBalance(long accountNumber,double balance);
    public BankAccount getBankAccount(long accountNumber);
    public List<BankAccount> getAllBankAccounts();
}
