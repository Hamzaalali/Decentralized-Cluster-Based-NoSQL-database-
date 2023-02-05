package org.example;
import org.example.database.BankDatabase;
import org.example.services.BankServices;
import java.util.Scanner;

public class BankingApp {
    public static void main(String arg[]) {

        BankServices bankServices=new BankServices();
        BankDatabase.getInstance().init();
        Scanner sc = new Scanner(System.in);
        int ch;
        System.out.println("\n ***Banking System Application***");
        do {
            System.out.println("1. Open Account \n2. Display All Accounts \n3. Search By Account Number \n4. Deposit \n5. Withdraw \n6. Delete Account \n7.Exit ");
            System.out.println("Enter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
                case 1:
                    bankServices.openAccount();
                    System.out.println("-------------------------------------------");
                    break;
                case 2:
                    bankServices.showAllAccounts();
                    System.out.println("-------------------------------------------");
                    break;
                case 3:
                    bankServices.showAccount();
                    System.out.println("-------------------------------------------");
                    break;
                case 4:
                    bankServices.deposit();
                    System.out.println("-------------------------------------------");
                    break;
                case 5:
                    bankServices.withdrawal();
                    System.out.println("-------------------------------------------");
                    break;
                case 6:
                    bankServices.deleteAccount();
                    System.out.println("-------------------------------------------");
                    break;
                case 7:
                    System.out.println("See you soon...");
                    break;
            }
        }
        while (ch != 7);
    }
}
