package org.example.services;

import org.example.database.collection.bank.account.BankAccount;
import org.example.database.collection.bank.account.BankAccountTypes;
import org.example.database.collection.bank.account.NoSqlBankAccountDao;

import java.util.List;
import java.util.Scanner;

public class BankServices {
    Scanner sc = new Scanner(System.in);
    public void openAccount() {
        System.out.print("Enter Account number(unique): ");
        long accountNumber=sc.nextLong();
        System.out.print("Enter Client Name: ");
        String clientName=sc.next();
        System.out.print("Enter Account type(saving/salary): ");
        String accountType=sc.next();
        System.out.print("Enter Balance: ");
        double balance=sc.nextDouble();
        System.out.print("Do you have insurance?(true/false): ");
        boolean hasInsurance=sc.nextBoolean();
        BankAccount bankAccount=new BankAccount();
        bankAccount.setClientName(clientName);
        bankAccount.setAccountType(BankAccountTypes.valueOf(accountType.toUpperCase()));
        bankAccount.setBalance(balance);
        bankAccount.setHasInsurance(hasInsurance);
        bankAccount.setAccountNumber(accountNumber);
        NoSqlBankAccountDao.getInstance().addBankAccount(bankAccount);
    }
    public void showAccount() {
        System.out.print("Enter Account Number Please: ");
        long accountNumber=sc.nextLong();
        BankAccount account=NoSqlBankAccountDao.getInstance().getBankAccount(accountNumber);
        if(account==null){
            System.out.println("No Account Found With This Number");
        }else{
            System.out.println("Account");
            System.out.println("--------");
            System.out.println(account);
        }
    }
    public void deposit() {
        System.out.print("Enter Account Number Please: ");
        long accountNumber=sc.nextLong();
        System.out.println("Enter the amount you want to deposit: ");
        double amount=sc.nextDouble();
        BankAccount account=NoSqlBankAccountDao.getInstance().getBankAccount(accountNumber);
        if(account==null){
            System.out.println("No Account Found With This Number");
            return;
        }
        NoSqlBankAccountDao.getInstance().updateBankAccountBalance(accountNumber,account.getBalance()+amount);
        System.out.println("Balance after deposit: " + (account.getBalance()+amount));

    }
    public void withdrawal() {
        System.out.print("Enter Account Number Please: ");
        long accountNumber=sc.nextLong();
        System.out.println("Enter the amount you want to withdraw: ");
        double amount=sc.nextDouble();
        BankAccount account=NoSqlBankAccountDao.getInstance().getBankAccount(accountNumber);
        if(account==null){
            System.out.println("No Account Found With This Number");
            return;
        }
        if (account.getBalance() >= amount) {
            NoSqlBankAccountDao.getInstance().updateBankAccountBalance(accountNumber,account.getBalance()-amount);
            System.out.println("Balance after withdrawal: " + (account.getBalance()-amount));
        } else {
            System.out.println("Your balance is less than " + amount + "\tTransaction failed...!!" );
        }
    }
    public void showAllAccounts() {
        List<BankAccount> accounts=NoSqlBankAccountDao.getInstance().getAllBankAccounts();
        int account=1;
        for(BankAccount bankAccount:accounts){
            System.out.println("Account "+account++);
            System.out.println("--------");
            System.out.println(bankAccount);
        }
    }
    public void deleteAccount(){
        System.out.print("Enter Account Number Please: ");
        long accountNumber=sc.nextLong();
        NoSqlBankAccountDao.getInstance().deleteBankAccount(accountNumber);
    }
}